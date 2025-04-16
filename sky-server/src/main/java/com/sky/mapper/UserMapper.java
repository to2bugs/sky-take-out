package com.sky.mapper;

import com.sky.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    /**
     * 查询当前微信用户是不是新用户
     * @param openid
     * @return
     */
    User getByOpenid(String openid);

    /**
     * 存入用户信息
     * @param newUser
     */
    void insert(User newUser);
}
