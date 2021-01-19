package com.calculatorserver.demoproject.dto;

import com.calculatorserver.demoproject.entity.Calculation;
import com.calculatorserver.demoproject.entity.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CalculationDtoTest {

    @Test
    void convertListToDto() {
        List<Calculation> calculations = new ArrayList<>();
        Date date = new Date();
        User user = new User();
        user.setLogin("test");
        CalculationDto calculationDto = new CalculationDto();
        Calculation calculation1 = new Calculation(1L, date, user, false, "2+2", 3);
        Calculation calculation2 = new Calculation(2L, date, user, false, "2+3", 3);
        calculations.add(calculation1);
        calculations.add(calculation2);
        List<CalculationDto> calculationDtos = calculationDto.convertListToDto(calculations);

        List<CalculationDto> calculationsDtos = new ArrayList<>();
        CalculationDto calculationDto1 = new CalculationDto(1L, date, user.getUsername(), false, "2+2", 3);
        CalculationDto calculationDto2 = new CalculationDto(2L, date, user.getUsername(), false, "2+3", 3);
        calculationsDtos.add(calculationDto1);
        calculationsDtos.add(calculationDto2);

        assertEquals(calculationDtos, calculationsDtos);
    }
}