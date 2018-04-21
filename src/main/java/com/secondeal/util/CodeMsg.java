package com.secondeal.util;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CodeMsg {
    private int retCode;
    private String message;
    private String data;
    // 按照模块定义CodeMsg
    // 通用异常
    public static CodeMsg SUCCESS = new CodeMsg(0, "success");
    public static CodeMsg SERVER_EXCEPTION = new CodeMsg(500100, "服务端异常");
    public static CodeMsg PARAMETER_ISNULL = new CodeMsg(500101, "输入参数为空");
    // 业务异常
    public static CodeMsg USER_NOT_EXSIST = new CodeMsg(500102, "用户不存在");
    public static CodeMsg USER_IS_EXSIST = new CodeMsg(500103, "该手机号已被注册");
    public static CodeMsg PASSWORD_ERROR = new CodeMsg(500102, "密码错误", "null");
    public static CodeMsg ONLINE_USER_OVER = new CodeMsg(500103, "在线用户数超出允许登录的最大用户限制。");
    public static CodeMsg SESSION_NOT_EXSIST = new CodeMsg(500104, "不存在离线session数据");
    public static CodeMsg NOT_FIND_DATA = new CodeMsg(500105, "查找不到对应数据");

    private CodeMsg(int retCode, String message) {
        this.retCode = retCode;
        this.message = message;
    }

    private CodeMsg(int retCode, String message, String data) {
        this.retCode = retCode;
        this.message = message;
        this.data = data;
    }

}