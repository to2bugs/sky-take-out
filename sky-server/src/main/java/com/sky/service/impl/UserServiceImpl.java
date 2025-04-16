package com.sky.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sky.constant.MessageConstant;
import com.sky.dto.UserLoginDTO;
import com.sky.entity.User;
import com.sky.exception.LoginFailedException;
import com.sky.mapper.UserMapper;
import com.sky.properties.WeChatProperties;
import com.sky.service.UserService;
import com.sky.utils.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private WeChatProperties weChatProperties;

    @Autowired
    private UserMapper userMapper;

    /**
     * 微信登录
     * @param userLoginDTO
     * @return
     */
    @Override
    public User login(UserLoginDTO userLoginDTO) {
        // 1, 通过HttpClient构造登录凭证校验请求
        //    构造请求参数
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("appid", weChatProperties.getAppid());
        paramMap.put("secret", weChatProperties.getSecret());
        paramMap.put("js_code", userLoginDTO.getCode());
        paramMap.put("grant_type", "authorization_code");
        //   调用工具类HttpClientUtil发送请求
        String res = HttpClientUtil.doGet("https://api.weixin.qq.com/sns/jscode2session", paramMap);
        log.info("微信登录返回结果：{}", res);

        // 2, 解析微信后端的响应结果，获取openid
        JSONObject jsonObject = JSON.parseObject(res);
        String openid = (String) jsonObject.get("openid");
        if (openid == null) throw new LoginFailedException(MessageConstant.USER_NOT_LOGIN);

        // 3, 判断是不是新用户，即使用openid查询user表
        User user = userMapper.getByOpenid(openid);


        if (user == null) {
            // 4，如果是新用户，则初始化用户数据到user表
            User newUser = new User();
            newUser.setOpenid(openid);
            newUser.setCreateTime(LocalDateTime.now());
            newUser.setName(openid.substring(0, 5));
            //   将新用户数据写入数据库
            userMapper.insert(newUser);
            return newUser;
        }

        // 5，如果是老用户就直接返回User
        return user;
    }
}
