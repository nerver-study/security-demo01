package com.lzw.auth.securitydemo01.config;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {


    /**
     * 这个是默认的，可以不写在
     * 在这个EnableWebSecurity里是写了的
     * 这个是过滤器链
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //authorizeRequests()：开启授权保护
        //anyRequest()：对所有请求开启授权保护
        //authenticated()：已认证请求会自动被授权
        http
                .authorizeRequests(authorize -> authorize.anyRequest().authenticated())
//                .formLogin(withDefaults())//表单授权方式
                .httpBasic(withDefaults());//基本授权方式


            //关闭csrf攻击防御,post请求会有这种情况
            http.csrf((csrf) -> {
                csrf.disable();
            });

        http.formLogin( form -> {
            form
                    .loginPage("/login").permitAll() //登录页面无需授权即可访问
                    .usernameParameter("username") //自定义表单用户名参数，默认是username
                    .passwordParameter("password") //自定义表单密码参数，默认是password
                    .failureUrl("/login?error") //登录失败的返回地址
                    .successHandler(new MyAuthenticationSuccessHandler()) //认证成功时的处理
                    .failureHandler(new MyAuthenticationFailureHandler()) //认证失败时的处理
            ;
        }); //使用表单授权方式

        http.logout(logout -> {
            logout.logoutSuccessHandler(new MyLogoutSuccessHandler()); //注销成功时的处理
        });

        //错误处理
        http.exceptionHandling(exception  -> {
            exception.authenticationEntryPoint(new MyAuthenticationEntryPoint());//请求未认证的接口
        });
        return http.build();
    }



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
//    @Bean
//    public UserDetailsService userDetailsService() {
//        //测试：使用数据库中配置的用户名和密码进行登录
//        //或者直接在DBUserDetailsManager类上添加@Component注解
//        DBUserDetailsManager manager = new DBUserDetailsManager();
//        return manager;
//    }
}