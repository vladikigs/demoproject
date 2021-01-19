package com.calculatorserver.demoproject.repository;

import com.calculatorserver.demoproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import com.calculatorserver.demoproject.entity.Calculation;

import java.util.List;

public interface CalculationRepository extends JpaRepository<Calculation, Long> {

    List<Calculation> findAllByUser(User user);

}
