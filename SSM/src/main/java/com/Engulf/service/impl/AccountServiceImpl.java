package com.Engulf.service.impl;

import com.Engulf.dao.IAccountDao;
import com.Engulf.domain.Account;
import com.Engulf.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("accountService")
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private IAccountDao accountDao;

    @Override
    public List<Account> findAll() {
        System.out.println("业务层查询所有账户信息...");
        return accountDao.findAll();
    }

    @Override
    public void saveAccount(Account account) {
        accountDao.saveAccount(account);
        System.out.println("业务层保存用户...");
    }
}
