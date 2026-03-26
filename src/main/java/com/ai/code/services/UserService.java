package com.ai.code.services;

import com.ai.code.entities.User;
import com.ai.code.exceptions.UserNotFoundException;
import com.ai.code.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String name, String email, String password) {
        if (isBlank(name) || isBlank(email) || isBlank(password)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "name, email and password are required");
        }

        String normalizedEmail = email.trim().toLowerCase();

        userRepository.findByEmail(normalizedEmail).ifPresent(existing -> {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already in use");
        });

        User user = new User();
        user.setName(name);
        user.setEmail(normalizedEmail);
        user.setPassword(password);
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id is required");
        }

        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }
    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}

