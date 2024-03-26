package com.waga.quickanswer.mybatis.dao;

import com.waga.quickanswer.mybatis.domain.LoginUserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginUserInfoDao {
    void insert(LoginUserInfo loginUserInfo);
    void insertSimple(LoginUserInfo loginUserInfo);
    void delete(String id);
}
