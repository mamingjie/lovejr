package com.mamingjie.lovejr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mamingjie.lovejr.model.City;
import com.mamingjie.lovejr.model.CityRepository;

@RestController
@RequestMapping("/city")
public class CityController {

	@Autowired
	CityRepository cityRepository;
	
	@RequestMapping("/add")
	City add(City city) {
		return cityRepository.save(city);
	}
	
	@RequestMapping("/list")
	Iterable<City> list() {
		return cityRepository.findAll();
	}
}
