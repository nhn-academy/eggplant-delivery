package com.nhnacademy.eggplantdelivery.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {

    @GetMapping("/")
    public String main() {
        return "Hello,!";
    }
}
