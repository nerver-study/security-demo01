package com.lzw.auth.securitydemo01.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lzw.auth.securitydemo01.entity.User;
import com.lzw.auth.securitydemo01.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class DBUserDetailsManager implements UserDetailsManager, UserDetailsPasswordService {

    @Resource
    private UserMapper userMapper;

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("username", username);
//        User user = userMapper.selectOne(queryWrapper);
//        if (user == null) {
//            throw new UsernameNotFoundException(username);
//        } else {
//            Collection<GrantedAuthority> authorities = new ArrayList<>();
////            authorities.add(()->"USER_LIST");
//            authorities.add(()->"USER_ADD");
//
//            /*authorities.add(new GrantedAuthority() {
//                @Override
//                public String getAuthority() {
//                    return "USER_LIST";
//                }
//            });
//            authorities.add(new GrantedAuthority() {
//                @Override
//                public String getAuthority() {
//                    return "USER_ADD";
//                }
//            });*/
//
//            return new org.springframework.security.core.userdetails.User(
//                    user.getUsername(),
//                    user.getPassword(),
//                    user.getEnabled(),
//                    true, //用户账号是否过期
//                    true, //用户凭证是否过期
//                    true, //用户是否未被锁定
//                    authorities); //权限列表
//        }
//    }
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }else{
            return org.springframework.security.core.userdetails.User
                    .withUsername(user.getUsername())
                    .password(user.getPassword())
                    .roles("ADMIN")
//                    .authorities("USER_ADD", "USER_UPDATE")  //与上面的 .roles("ADMIN")不可同时使用
                    .credentialsExpired(false)
                    .accountLocked(false)
                    .disabled(!user.getEnabled())
                    .build();
//            return org.springframework.security.core.userdetails.User
//                    .withUsername(user.getUsername())
//                    .password(user.getPassword())
//                    .credentialsExpired(false)
//                    .accountLocked(false)
//                    .disabled(!user.getEnabled())
////                    .roles("ADMIN")
//                    .roles("User")
//                    .build();
        }
    }

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        return null;
    }

    @Override
    public void createUser(UserDetails userDetails) {

        User user = new User();
        user.setUsername(userDetails.getUsername());
        user.setPassword(userDetails.getPassword());
        user.setEnabled(true);
        userMapper.insert(user);
    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return false;
    }
}