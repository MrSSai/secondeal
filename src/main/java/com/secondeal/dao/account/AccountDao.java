package com.secondeal.dao.account;

import com.secondeal.model.account.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


@Mapper
public interface AccountDao {

    String isExist(@Param("mobile") String name);

    int insert(Account account);

    List<Account> queryAccount(@Param("name") String name);

    List<Account> getUser();

    Map selectByMobile(@Param("mobile") String mobile);
}