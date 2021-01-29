package com.calculatorserver.demoproject.controller;

import com.calculatorserver.demoproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RegisterController {


    @Autowired
    UserService userService;

    //http://localhost:8080/register?l=userName&p=password
    @GetMapping("/register")
    @ResponseBody
    public String createUser(@RequestParam(name = "l") String login, @RequestParam(name = "p") String password) {
        return userService.registerUser(login, password).toString();
    }

}
