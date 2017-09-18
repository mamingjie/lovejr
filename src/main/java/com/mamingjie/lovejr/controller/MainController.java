package com.mamingjie.lovejr.controller;

import com.aliyun.oss.OSSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mamingjie.lovejr.service.BaseService;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@Controller
public class MainController {

	@Autowired
	OSSClient ossClient;

	@Autowired
	private StringRedisTemplate template;
	
	@RequestMapping("/")
	void index(HttpServletResponse response) throws IOException {
		response.sendRedirect("/index");
	}

	@RequestMapping("/index")
	ModelAndView index() {
		return new ModelAndView("index", "message", "message1");
	}

	@RequestMapping("/hello")
	ModelAndView hello() {
		return new ModelAndView("hello");
	}

	@RequestMapping("/redis")
	@ResponseBody
	String testRedis(String key, String value) {
		if (key != null && key.length() != 0) {
			ValueOperations<String, String> ops = this.template.opsForValue();
			if (value != null && value.length() != 0) {
				ops.set(key, value);
				return "set: " + key + " = " + value;
			}
			return "get: " + key + " = " + ops.get(key);
		}

		return "error";
	}

	@RequestMapping("/oss")
	String oss() {
		// ossClient.
		ossClient.putObject("lovejr-bucket", "vue.js", new File("/Users/mamingjie/vue.js"));
		return null;
	}

}
