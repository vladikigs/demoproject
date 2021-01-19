package com.calculatorserver.demoproject.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Calculation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dateCalculations;
    @OneToOne
    private User user;
    private Boolean isCalculationPerformed;
    private String expression;
    private Integer precision;



}
