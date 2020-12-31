package com.Engulf.Test;

import com.Engulf.dao.IAccountDao;
import com.Engulf.domain.Account;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestMybatis {
    /**
     * 查询所有账户
     * @throws Exception
     */
    @Test
    public void run() throws Exception {
        //加载配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //创建SqlSessionFactory对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //创建SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取代理对象
        IAccountDao accountDao = sqlSession.getMapper(IAccountDao.class);
        //查询所有数据
        List<Account> accounts = accountDao.findAll();
        for(Account account:accounts){
            System.out.println(account);
        }
        //释放资源
        sqlSession.close();
        in.close();
    }


    /**
     * 测试保存操作
     * @throws Exception
     */
    @Test
    public void run2() throws Exception {
        Account account = new Account();
        account.setName("Kindred");
        account.setMoney(500d);
        //加载配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //创建SqlSessionFactory对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //创建SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取代理对象
        IAccountDao accountDao = sqlSession.getMapper(IAccountDao.class);
        //保存操作
        accountDao.saveAccount(account);

        //提交事务
        sqlSession.commit();

        //释放资源
        sqlSession.close();
        in.close();
    }
}
