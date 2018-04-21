package com.secondeal.service.account;

import com.secondeal.model.account.Account;

import java.util.List;

public interface AccountI {
    boolean isExist(String name);

    int insert(Account account);

    List<Account> queryAccount(String name);

    List<Account> getUser();
}
