package com.ai.code.controllers;

import com.ai.code.controllers.dto.CreateUserRequest;
import com.ai.code.controllers.dto.UserDetailsResponse;
import com.ai.code.controllers.dto.UpdateUserRequest;
import com.ai.code.entities.User;
import com.ai.code.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PatchMapping;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping
    public ResponseEntity<UserDetailsResponse> createUser(@RequestBody CreateUserRequest request) {
        User created = userService.createUser(request.getName(), request.getEmail(), request.getPassword());

        UserDetailsResponse response = new UserDetailsResponse(
                created.getName(),
                created.getEmail()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDetailsResponse> getUserDetails(@PathVariable Long id) {
        User user = userService.getUserById(id);

        UserDetailsResponse response = new UserDetailsResponse(
                user.getName(),
                user.getEmail()
        );

        return ResponseEntity.ok(response);
    }

}

