package com.Engulf.service;

import com.Engulf.domain.Account;

import java.util.List;

public interface IAccountService {
    /**
     * 查询所有账户信息
     * @return
     */
    List<Account> findAll();

    /**
     * 保存账户信息
     * @param account
     */
    void saveAccount(Account account);
}
