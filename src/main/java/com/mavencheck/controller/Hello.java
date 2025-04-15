package com.mavencheck.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.StandardEmitterMBean;

@RestController
@RequestMapping("/api")
public class Hello {


    @GetMapping("/get")
    public String hello(){
        return "hello";
    }
}
