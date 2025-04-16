package com.mavencheck.dto;

public class UserResponseDTO {
    private String firstName;
    private String email;

    private String lastName;
    // constructor
    public UserResponseDTO(String firstName, String email,String lastName) {
        this.firstName = firstName;
        this.email = email;
        this.lastName=lastName;
    }

    // getters and setters

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
