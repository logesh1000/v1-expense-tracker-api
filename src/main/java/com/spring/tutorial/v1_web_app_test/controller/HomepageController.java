package com.spring.tutorial.v1_web_app_test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomepageController {
	
	@RequestMapping("/")
	@ResponseBody
	public String getHomePageData() {
		return "Hi";
	}

}
