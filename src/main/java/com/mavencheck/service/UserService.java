package com.mavencheck.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mavencheck.dto.UserResponseDTO;
import com.mavencheck.entity.UserWrapper;
import com.mavencheck.exception.DuplicateUserException;
import com.mavencheck.exception.UsernotFoundException;
import com.mavencheck.model.UserDetails;
import com.mavencheck.repo.UserWrapperRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service

public class UserService {

    private final UserWrapperRepository repository;
    private final ObjectMapper objectMapper;

    public UserService(UserWrapperRepository repository, ObjectMapper objectMapper) {
        this.repository = repository;
        this.objectMapper = objectMapper;
    }

    public UserWrapper saveUser(UserDetails userDetails) {
        try {
            String json = objectMapper.writeValueAsString(userDetails);

            if (repository.existsByJson(json)) {
                throw new DuplicateUserException("Duplicate user entry found.");
            }

            UserWrapper wrapper = new UserWrapper();
            wrapper.setUserDetailsJson(objectMapper.readTree(json));
            return repository.save(wrapper);

        } catch (JsonProcessingException e) {
            throw new RuntimeException("Invalid user details", e);
        }
    }


    public UserWrapper getUserById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new UsernotFoundException("User not found with id: " + id));
    }

    public UserResponseDTO getUserByFirstName(String firstName) {
        UserWrapper user = repository.findByFirstName(firstName)
                .orElseThrow(() -> new UsernotFoundException("User not found with this FirstName: " + firstName));

        JsonNode json = user.getUserDetailsJson();
        return new UserResponseDTO(
                json.has("firstName") ? json.get("firstName").asText() : null,
                json.has("email") ? json.get("email").asText() : null,
                json.has("lastName") ? json.get("lastName").asText() : null
        );
    }

}


