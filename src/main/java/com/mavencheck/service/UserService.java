package com.mavencheck.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mavencheck.entity.UserWrapper;
import com.mavencheck.exception.DuplicateUserException;
import com.mavencheck.model.UserDetails;
import com.mavencheck.repo.UserWrapperRepository;
import org.springframework.stereotype.Service;


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


}
