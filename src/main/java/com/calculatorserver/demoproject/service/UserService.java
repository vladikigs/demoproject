package com.calculatorserver.demoproject.service;

import com.calculatorserver.demoproject.config.ApplicationRole;
import com.calculatorserver.demoproject.entity.User;
import com.calculatorserver.demoproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User loadUserByUsername(String login) {
        return userRepository.findAllByLogin(login);
    }

    public User registerUser(String login, String password) {
        User user = new User(
                null,
                login,
                passwordEncoder.encode(password),
                true,
                true,
                true,
                true,
                ApplicationRole.USER);
        return  userRepository.save(user);
    }
}
