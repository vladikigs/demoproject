package com.calculatorserver.demoproject.repository;

import com.calculatorserver.demoproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findAllByLogin(String login);

}
