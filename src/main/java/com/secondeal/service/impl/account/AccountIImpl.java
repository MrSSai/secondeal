package com.secondeal.service.impl.account;

import com.secondeal.dao.account.AccountDao;
import com.secondeal.model.account.Account;
import com.secondeal.service.account.AccountI;
import com.secondeal.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountIImpl implements AccountI {
    @Autowired
    AccountDao accountDao;

    @Override
    public boolean isExist(String name) {
        String res = accountDao.isExist(name);
        if (res != null) {
            return true;
        }
        return false;
    }

    @Override
    public int insert(Account account) {

        return accountDao.insert(account);
    }

    @Override
    public List<Account> queryAccount(String name) {
        return accountDao.queryAccount(name);
    }

    @Override
    public List<Account> getUser() {
        return accountDao.getUser();
    }
}
