package com.calculatorserver.demoproject.controller;

import com.calculatorserver.demoproject.config.ApplicationRole;
import com.calculatorserver.demoproject.entity.User;
import com.calculatorserver.demoproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

public class RegisterController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    private User validate(@Validated User user) {
        return user;
    }

    //http://localhost:8080/register?l=userName&p=password
    @GetMapping("/register")
    @ResponseBody
    public String createUser(@RequestParam(name = "l") String login, @RequestParam(name = "p") String password) {
                User user  = new User(
                null,
                login,
                passwordEncoder.encode(password),
                true,
                true,
                true,
                true,
                ApplicationRole.USER);
        User validateUser = validate(user);
        userRepository.save(validateUser);
        return "ok";
    }

}
