package com.lzw.auth.securitydemo01.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller for "/".
 *
 * @author Joe Grandja
 */
@Controller
public class IndexController {

	@GetMapping("/")
	@ResponseBody
	public Map index() {

		System.out.println("index controller");

		SecurityContext context = SecurityContextHolder.getContext();//存储认证对象的上下文
		Authentication authentication = context.getAuthentication();//认证对象
		String username = authentication.getName();//用户名
		Object principal =authentication.getPrincipal();//身份
		Object credentials = authentication.getCredentials();//凭证(脱敏)
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();//权限

		System.out.println(username);
		System.out.println(principal);
		System.out.println(credentials);
		System.out.println(authorities);

		//创建结果对象
		HashMap result = new HashMap();
		result.put("code", 0);
		result.put("data", username);

		return result;
//		return "index";
	}

	/**
	 *测试springsec做了什么
	 * 对于每一个请求都得是登录的状态
	 * 一次会话如果登录了就是登录的，一个浏览器登录了，换一个浏览器还需要登录
	 * 同一个浏览器登录了，在开一个页面不需要登录
	 * @return
	 */
	@GetMapping("/indexa")
	@ResponseBody
	public String indexa() {
		return "indexa";
	}

}