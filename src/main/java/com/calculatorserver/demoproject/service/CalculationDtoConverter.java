package com.calculatorserver.demoproject.service;

import com.calculatorserver.demoproject.dto.CalculationDto;
import com.calculatorserver.demoproject.entity.Calculation;
import org.springframework.stereotype.Service;

@Service
public class CalculationDtoConverter extends AbstractConverter<Calculation, CalculationDto> {


    public CalculationDto convertToDto(Calculation calculation) {
        return new CalculationDto(calculation.getId(),
                calculation.getDateCalculations(),
                calculation.getUser().getUsername(),
                calculation.getIsCalculationPerformed(),
                calculation.getExpression(),
                calculation.getPrecision());
    }

}
