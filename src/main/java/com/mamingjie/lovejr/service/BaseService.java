package com.mamingjie.lovejr.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BaseService {

	@Value("${test-prop}")
	String str;
	
	public String getStr() {
		return String.format("%s", str);
	}
}
