package com.lzw.auth.securitydemo01.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzw.auth.securitydemo01.entity.User;
import com.lzw.auth.securitydemo01.mapper.UserMapper;
import com.lzw.auth.securitydemo01.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}