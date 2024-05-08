package com.lzw.auth.securitydemo01.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzw.auth.securitydemo01.config.DBUserDetailsManager;
import com.lzw.auth.securitydemo01.entity.User;
import com.lzw.auth.securitydemo01.mapper.UserMapper;
import com.lzw.auth.securitydemo01.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    private DBUserDetailsManager dbUserDetailsManager;

    @Override
    public void saveUserDetails(User user) {

        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withDefaultPasswordEncoder()//默认的密码加密方式
                .username(user.getUsername()) //自定义用户名
                .password(user.getPassword()) //自定义密码
                .build();
        dbUserDetailsManager.createUser(userDetails);

    }
}