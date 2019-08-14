package com.feeling.tsukkomi.controller;

import com.feeling.tsukkomi.bean.Account;
import com.feeling.tsukkomi.bean.RespBean;
import com.feeling.tsukkomi.service.AccountService;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;


@RestController
public class AccountController {

    Logger logger = LoggerFactory.getLogger(AccountController.class);
    @Autowired
    AccountService accountService;

    @RequestMapping("/reg")
    public RespBean reg(Account account) {
        if (account.getPhoneNum().equals("") || account.getPassword().equals("")){
            return new RespBean("error","参数不能为空");
        }
        int result = accountService.reg(account);
        if (result == 0) {
            //成功
            logger.info(String.format("register success, phoneNum: %s",account.getPhoneNum()));
            return new RespBean("success", "注册成功!");
        } else if (result == 1) {
            return new RespBean("error", "手机号已被注册，注册失败!");
        } else {
            //失败
            return new RespBean("error", "注册失败!");
        }
    }

    @PostMapping("/login")
    public RespBean login(String phoneNum,String password, HttpSession session){
        if (phoneNum.equals("") || password.equals("")){
            return new RespBean("error","参数不能为空");
        }
        int result = accountService.login(phoneNum,password);
        if(result == 1){
            //成功
            session.setAttribute("loginUser",accountService.loadUserByPhoneNum(phoneNum).getNickName());
            logger.info(String.format("login success, phoneNum: %s",phoneNum));
            return new RespBean("success", "登录成功!");
        }else if (result == 0){
            return new RespBean("error", "密码错误!");
        }else if (result == 2){
            return new RespBean("error", "账号不存在!");
        }
        return new RespBean("error", "登录失败!");
    }

}
