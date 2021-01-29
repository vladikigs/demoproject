package com.calculatorserver.demoproject.service;

import com.calculatorserver.demoproject.dto.CalculateRequest;
import com.calculatorserver.demoproject.dto.CalculationDto;
import com.calculatorserver.demoproject.entity.User;
import com.calculatorserver.demoproject.entity.Calculation;
import com.calculatorserver.demoproject.repository.CalculationRepository;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class CalculationsHistoryService {

    private final CalculationRepository calculationRepository;
    private final CalculationDtoConverter calculationDtoConverter;

    public CalculationsHistoryService(CalculationRepository calculationRepository, CalculationDtoConverter calculationDtoConverter) {
        this.calculationRepository = calculationRepository;
        this.calculationDtoConverter = calculationDtoConverter;
    }

    public List<CalculationDto> getUserHistory(User user) {
        List<Calculation> userHistory = calculationRepository.findAllByUser(user);
        return calculationDtoConverter.convertListToDto(userHistory);
    }

    public void saveUserRequest(CalculateRequest calculateRequest, Boolean isCalculated, User user) {
        Calculation calculation = new Calculation(null,
                new Date(),
                user,
                isCalculated,
                calculateRequest.getExpression(),
                calculateRequest.getPrecision());
        calculationRepository.save(calculation);
    }

}
