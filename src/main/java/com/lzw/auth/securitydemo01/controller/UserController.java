package com.lzw.auth.securitydemo01.controller;

import com.lzw.auth.securitydemo01.entity.User;
import com.lzw.auth.securitydemo01.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    public UserService userService;

    //localhost:8080/user/list
//    @GetMapping("/list")
//    public List<User> getList(){
//        return userService.list();
//    }
//
//
//    @PostMapping("/add")
//    public void add(@RequestBody User user){
//        userService.saveUserDetails(user);
//    }

    //用户必须有 ADMIN 角色 并且 用户名是 admin 才能访问此方法
    @PreAuthorize("hasRole('ADMIN') and authentication.name == 'admin'")
    @GetMapping("/list")
    public List<User> getList(){
        return userService.list();
    }

    //用户必须有 USER_ADD 权限 才能访问此方法
    @PreAuthorize("hasAuthority('USER_ADD')")
    @PostMapping("/add")
    public void add(@RequestBody User user){
        userService.saveUserDetails(user);
    }
}