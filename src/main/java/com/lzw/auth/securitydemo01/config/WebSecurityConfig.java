package com.lzw.auth.securitydemo01.config;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    /**
     * 基于内存的用户认证
     * @return
     */

/*    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withDefaultPasswordEncoder().username("user").password("password").roles("USER").build());
        return manager;
    }*/


    /**
     * 基于数据库的用户认证
     * @return
     */
    @Bean
    public UserDetailsService userDetailsService() {
        //测试：使用数据库中配置的用户名和密码进行登录
        //或者直接在DBUserDetailsManager类上添加@Component注解
        DBUserDetailsManager manager = new DBUserDetailsManager();
        return manager;
    }
}