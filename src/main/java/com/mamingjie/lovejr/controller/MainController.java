package com.mamingjie.lovejr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mamingjie.lovejr.service.BaseService;

@Controller
public class MainController {

	@Autowired
	BaseService baseService;
	
	@RequestMapping("/")
	@ResponseBody
	String index() {
		return "hello " + baseService.getStr();
	}

}
