package com.calculatorserver.demoproject.service;


import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractConverter<S, T> {

    public List<T> convertListToDto(List<S> calculationArrayList) {
        return calculationArrayList.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public abstract T convertToDto(S source);

}
