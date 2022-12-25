package com.sommelier.wine4you.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/home")
public class HomeController {
    @GetMapping
    public String getStart() {
        return "Hello heroku!";
    }
}