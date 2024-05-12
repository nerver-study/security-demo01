package com.example.oauth2logindemo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    /**
     * 1. A 网站让用户跳转到 GitHub，并携带参数ClientID 以及 Redirection URI。
     * 2. GitHub 要求用户登录，然后询问用户"A 网站要求获取用户信息的权限，你是否同意？"
     * 3. 用户同意，GitHub 就会重定向回 A 网站，同时发回一个授权码。
     * 4. A 网站使用授权码，向 GitHub 请求令牌。
     * 5. GitHub 返回令牌.
     * 6. A 网站使用令牌，向 GitHub 请求用户数据。
     * 7. GitHub返回用户数据
     * 8. A 网站使用 GitHub用户数据登录
     * @param model
     * @param authorizedClient
     * @param oauth2User
     * @return
     */
    /**
     * 请求login页面，点击github，跳转github，根据回调地址进行获取用户信息请求令牌这些操作
     * 最后跳转回到/，这个是我们自己写的
     * @param model
     * @param authorizedClient
     * @param oauth2User
     * @return
     */
    @GetMapping("/")
    public String index(
            Model model,
            @RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient,
            @AuthenticationPrincipal OAuth2User oauth2User) {
        model.addAttribute("userName", oauth2User.getName());
        model.addAttribute("clientName", authorizedClient.getClientRegistration().getClientName());
        model.addAttribute("userAttributes", oauth2User.getAttributes());
        return "index";
    }
}