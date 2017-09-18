package com.mamingjie.lovejr.controller;

import com.mamingjie.lovejr.model.User;
import com.mamingjie.lovejr.model.UserRepository;
import com.mamingjie.lovejr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/login")
    String login() {
        return "login";
    }

    @RequestMapping("/add")
    @ResponseBody
    User add(User user) {
        return userService.add(user);
    }
}
