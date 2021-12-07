package com.example.restapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//localhos:8080/hello
@RestController
@RequestMapping("hello")
public class HelloController {
    @GetMapping
    public String getHello(){
        return "Hello everyone";
    }
}
