package com.mavencheck.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.mavencheck.dto.UserResponseDTO;
import com.mavencheck.entity.UserWrapper;
import com.mavencheck.model.UserDetails;
import com.mavencheck.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{id}")
    public ResponseEntity<UserWrapper> getUserById(@PathVariable Long id) {
        UserWrapper user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/firstname/{firstName}")
    public ResponseEntity<UserResponseDTO> getUsersByFirstName(@PathVariable String firstName) {
        UserResponseDTO users = userService.getUserByFirstName(firstName);
        return ResponseEntity.ok(users);
    }

}
