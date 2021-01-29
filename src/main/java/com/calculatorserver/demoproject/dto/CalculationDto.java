package com.calculatorserver.demoproject.dto;

import com.calculatorserver.demoproject.entity.Calculation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

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


}
