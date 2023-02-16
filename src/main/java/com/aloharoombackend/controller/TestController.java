package com.aloharoombackend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("/api/1")
    public String test() {
        return "hello";
    }
}
