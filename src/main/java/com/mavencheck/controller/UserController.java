package com.mavencheck.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.mavencheck.entity.UserWrapper;
import com.mavencheck.model.UserDetails;
import com.mavencheck.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserWrapper> saveUser(@RequestBody UserDetails userDetails) throws JsonProcessingException {
        return ResponseEntity.ok(userService.saveUser(userDetails));
    }
}
