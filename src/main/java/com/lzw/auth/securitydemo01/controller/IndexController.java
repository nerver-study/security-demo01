package com.lzw.auth.securitydemo01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller for "/".
 *
 * @author Joe Grandja
 */
@Controller
public class IndexController {

	@GetMapping("/")
	public String index() {
		return "index";
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