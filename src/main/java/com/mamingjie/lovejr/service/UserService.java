package com.mamingjie.lovejr.service;

import com.mamingjie.lovejr.model.User;
import com.mamingjie.lovejr.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Md5PasswordEncoder passwordEncoder;

    @Autowired
    private SaltSource saltSource;

    public User add(User user) {
        user.setPassword(passwordEncoder.encodePassword(user.getPassword(), saltSource.getSalt(null)));
        user.setStatus(2);
        user.setCreateTime(new Date());
        return userRepository.save(user);
    }
}
