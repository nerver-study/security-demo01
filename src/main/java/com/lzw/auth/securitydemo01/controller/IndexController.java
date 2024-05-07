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
	@GetMapping("/indexa")
	@ResponseBody
	public String indexa() {
		return "indexa";
	}

}