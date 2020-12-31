package com.Engulf.dao;

import com.Engulf.domain.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 账户dao接口
 */

@Repository
public interface IAccountDao {
    /**
     * 查询所有账户信息
     * @return
     */
    @Select("select * from account")
    List<Account> findAll();

    /**
     * 保存账户信息
     * @param account
     */
    @Insert("insert into account(name,money) values(#{name},#{money})")
    void saveAccount(Account account);
}
