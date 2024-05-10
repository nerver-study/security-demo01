package com.lzw.auth.securitydemo01.config;

import com.alibaba.fastjson2.JSON;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.HashMap;

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
//                .authorizeRequests(authorize -> authorize.anyRequest().authenticated())//任何用户都可以请求
//                .formLogin(withDefaults())//表单授权方式
                .httpBasic(withDefaults());//基本授权方式


            //关闭csrf攻击防御,post请求会有这种情况
            http.csrf((csrf) -> {
                csrf.disable();
            });



        //开启授权保护
        http.authorizeRequests(
                authorize -> authorize
                        //具有USER_LIST权限的用户可以访问/user/list
                        .requestMatchers("/user/list").hasAuthority("USER_LIST")
                        //具有USER_ADD权限的用户可以访问/user/add
                        .requestMatchers("/user/add").hasAuthority("USER_ADD")
                        //对所有请求开启授权保护
                        .anyRequest()
                        //已认证的请求会被自动授权
                        .authenticated()
        );
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
            exception.accessDeniedHandler((request, response, e)->{ //请求未授权的接口

                //创建结果对象
                HashMap result = new HashMap();
                result.put("code", -1);
                result.put("message", "没有权限");

                //转换成json字符串
                String json = JSON.toJSONString(result);

                //返回响应
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().println(json);
            });
        });

        //跨域
        http.cors(withDefaults());

        //会话管理  后登录的账号会使先登录的账号失效
        http.sessionManagement(session -> {
            session
                    .maximumSessions(1)
                    .expiredSessionStrategy(new MySessionInformationExpiredStrategy());
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