package com.feeling.tsukkomi.mapper;

import com.feeling.tsukkomi.bean.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AccountMapper {
    public Account loadUserByPhoneNum(@Param("phoneNum") String phoneNum);
    public int reg(Account account);
    public String getPwdByPhoneNum(@Param("phoneNum") String phoneNum);
}
