package com.lzw.auth.securitydemo01.controller;

import com.lzw.auth.securitydemo01.entity.User;
import com.lzw.auth.securitydemo01.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    public UserService userService;

    //localhost:8080/user/list
    @GetMapping("/list")
    public List<User> getList(){
        return userService.list();
    }


    @PostMapping("/add")
    public void add(@RequestBody User user){
        userService.saveUserDetails(user);
    }
}