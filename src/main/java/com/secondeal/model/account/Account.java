package com.secondeal.model.account;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class Account {
    private Long id;

    private String uuid;

    private String nickname;

    private String passwd;

    private String email;

    private String telephone;

    private String phone;

    private String createTime;

    private String updateTime;

    private String deleteTime;

    private Integer qq;

}