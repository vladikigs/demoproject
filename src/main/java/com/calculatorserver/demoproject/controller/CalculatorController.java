package com.calculatorserver.demoproject.controller;

import com.calculatorserver.demoproject.dto.CalculateRequest;
import com.calculatorserver.demoproject.dto.CalculationDto;
import com.calculatorserver.demoproject.entity.User;
import com.calculatorserver.demoproject.service.CalculationsHistoryService;
import com.calculatorserver.demoproject.service.CalculatorService;
import com.calculatorserver.demoproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CalculatorController {

    @Autowired
    CalculationsHistoryService calculationsHistoryService;
    @Autowired
    UserService userService;
    @Autowired
    CalculatorService calculatorService;

    @PostMapping("/calculator")
    @ResponseBody
    public String serviceCalculator(@Valid @RequestBody CalculateRequest calculateRequest,
                                    Authentication authentication) {
        User user = userService.loadUserByUsername(authentication.getName());
        return calculatorService.calculateExpression(user, calculateRequest);
    }

    @GetMapping("/get-calculations-history/{login}")
    @ResponseBody
    public List<CalculationDto> getHistory(@PathVariable(name = "login") String login) {
        User user = userService.loadUserByUsername(login);
        return calculationsHistoryService.getUserHistory(user);
    }


}
