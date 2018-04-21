package com.secondeal.controller.account;

import com.secondeal.controller.BaseController;
import com.secondeal.model.account.Account;
import com.secondeal.service.account.AccountI;
import com.secondeal.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/account")
public class AccountController extends BaseController {

    @Autowired
    AccountI accountI;

    @PostMapping("/register")
    public Object register() {
        String encryptedPwd = null;
        Map<String, Object> parameters = this.getParam();
        String uuid = StringUtil.getUUID();
        String name = StringUtil.parseString(parameters.get("mobile"));
        Integer qq = StringUtil.parseInteger(parameters.get("qq"));
        String nickName = StringUtil.parseString(parameters.get("nickName"));
        String phone = StringUtil.parseString(parameters.get("phone"));
        String email = StringUtil.parseString(parameters.get("email"));
        Map<String, Object> m = new HashMap<>();
        Map<String, Object> m1 = new HashMap<>();
        m.put("username", name);
        m.put("uuid", uuid);
        boolean mobile = accountI.isExist(name);
        String passwd = StringUtil.parseString(parameters.get("password"));
        if (mobile) {
            return Result.error(CodeMsg.USER_IS_EXSIST);
        } else {
            try {
                encryptedPwd = Md5SaltTool.getEncryptedPwd(passwd);
                Account account = new Account();
                account.setUuid(uuid);
                account.setPasswd(encryptedPwd);
                account.setTelephone(name);
                account.setEmail(email);
                account.setQq(qq);
                account.setNickname(nickName);
                account.setPhone(phone);
                account.setCreateTime(StringUtil.getNowDate());
                int res = accountI.insert(account);
                if (res == 1) {
                    String token = JavaWebToken.createJavaWebToken(m);
                    m1.put("token", token);
                    m1.put("nickName", nickName);
                    return Result.success(m1);
                }
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("token", name);
            return Result.success(map);
        }

    }


    @PostMapping("/login")
    public Object login() {
        Map<String, Object> parameters = this.getParam();
        Map<String, Object> m = new HashMap<>();
        Map<String, Object> m1 = new HashMap<>();
        String name = StringUtil.parseString(parameters.get("username"));
        m.put("username", name);

        List<Account> list = accountI.queryAccount(name);
        if (list.size() != 0) {
            String password = list.get(0).getPasswd();
            String nickname = list.get(0).getNickname();
            String uuid = list.get(0).getUuid();
            m.put("uuid", uuid);
            String passwd = StringUtil.parseString(parameters.get("password"));
            if (null != name) { // 该用户存在
                try {
                    if (Md5SaltTool.validPassword(passwd, password)) {
                        // 返回登录token
                        String token = JavaWebToken.createJavaWebToken(m);
                        m1.put("token", token);
                        m1.put("nickName", nickname);
                        return Result.success(m1);
                    }
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return Result.error(CodeMsg.PASSWORD_ERROR);
            }
        }
        return Result.error(CodeMsg.USER_NOT_EXSIST, "aaaa");
    }

//    @GetMapping("/getuser")
//    public Object getUser() {
//        List<Account> list = accountI.getUser();
//        return list;
//    }

}
