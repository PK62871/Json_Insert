package com.mavencheck.entity;

import com.fasterxml.jackson.databind.JsonNode;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Entity
@Table(name = "xyz")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class UserWrapper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Type(type = "jsonb")
    @Column(name = "user_details_json", columnDefinition = "jsonb")
    private JsonNode userDetailsJson;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public JsonNode getUserDetailsJson() {
        return userDetailsJson;
    }

    public void setUserDetailsJson(JsonNode userDetailsJson) {
        this.userDetailsJson = userDetailsJson;
    }
}
