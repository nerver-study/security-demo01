package com.lzw.auth.securitydemo01.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lzw.auth.securitydemo01.entity.User;

public interface UserService extends IService<User> {
    void saveUserDetails(User user);
}