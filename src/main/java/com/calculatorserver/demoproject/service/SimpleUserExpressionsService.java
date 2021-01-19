package com.calculatorserver.demoproject.service;

import com.calculatorserver.demoproject.dto.CalculateRequest;
import com.calculatorserver.demoproject.entity.User;
import com.calculatorserver.demoproject.entity.Calculation;
import com.calculatorserver.demoproject.repository.CalculationRepository;
import com.calculatorserver.demoproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SimpleUserExpressionsService {

    @Autowired
    CalculationRepository calculationRepository;
    @Autowired
    UserRepository userRepository;

    public List<Calculation> getUserExpressions(User user) {
        return calculationRepository.findAllByUser(user);
    }

    public void addCalculation(CalculateRequest calculateRequest, Boolean isCalculated, User user) {
        Calculation calculation = new Calculation(null,
                new Date(),
                user,
                isCalculated,
                calculateRequest.getExpression(),
                calculateRequest.getPrecision());
        calculationRepository.save(calculation);
    }

    public User getUserByLogin(String login) {
        return userRepository.findAllByLogin(login);
    }

}
