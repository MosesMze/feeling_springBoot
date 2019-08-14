package com.feeling.tsukkomi.service;

import com.feeling.tsukkomi.bean.Account;
import com.feeling.tsukkomi.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

@Service
@Transactional
public class AccountService {

    @Autowired
    AccountMapper accountMapper;

    public Account loadUserByPhoneNum(String phoneNum){
        Account account = accountMapper.loadUserByPhoneNum(phoneNum);
        return account;
    }

    public String getPwdByPhoneNum(String phoneNum){
        return accountMapper.getPwdByPhoneNum(phoneNum);
    }

    /**
     * @param account
     * @return 0表示成功
     * 1表示手机号已经被注册
     * 2表示失败
     */
    public int reg(Account account){
        Account loadUserByPhoneNum = loadUserByPhoneNum(account.getPhoneNum());
        if (loadUserByPhoneNum != null){
            return 1;
        }
        //插入用户,插入之前先对密码进行加密
        account.setPassword(DigestUtils.md5DigestAsHex(account.getPassword().getBytes()));
        int result = accountMapper.reg(account);
        if (result==1){
            return 0;
        }
        return 2;
    }

    public int login(String phoneNum,String password){
        if (DigestUtils.md5DigestAsHex(password.getBytes()).equals(getPwdByPhoneNum(phoneNum))){
            return 1;
        }
        if (loadUserByPhoneNum(phoneNum)==null){
            return 2;
        }
        return 0;
    }
}
