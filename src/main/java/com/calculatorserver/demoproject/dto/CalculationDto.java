package com.calculatorserver.demoproject.dto;

import com.calculatorserver.demoproject.entity.Calculation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalculationDto {

    private Long id;
    private Date dateCalculation;
    private String userName;
    private Boolean isCalculationPerformed;
    private String expression;
    private Integer precision;

    public List<CalculationDto> convertListToDto(List<Calculation> calculationArrayList) {
        return calculationArrayList.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public CalculationDto convertToDto(Calculation calculation) {
        return new CalculationDto(calculation.getId(),
                calculation.getDateCalculations(),
                calculation.getUser().getUsername(),
                calculation.getIsCalculationPerformed(),
                calculation.getExpression(),
                calculation.getPrecision());
    }

}
