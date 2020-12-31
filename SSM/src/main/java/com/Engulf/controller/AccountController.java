package com.Engulf.controller;

import com.Engulf.domain.Account;
import com.Engulf.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 账户web控制器
 */
@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private IAccountService accountService;

    @RequestMapping("/findAll")
    public String findAll(Model model){
        System.out.println("表现层：查询所有账户");

        //调用service的方法
        List<Account> accounts = accountService.findAll();
        model.addAttribute("AccountList",accounts);

        return "list";
    }


    /**
     * 保存账户
     * @return
     */
    @RequestMapping("/save")
    public void save(Account account, HttpServletRequest request, HttpServletResponse response) throws IOException {
        accountService.saveAccount(account);
        System.out.println(request.getContextPath());
        response.sendRedirect(request.getContextPath()+"/account/findAll");
        return;
    }
}
