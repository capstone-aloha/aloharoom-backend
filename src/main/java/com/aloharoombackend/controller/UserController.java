package com.aloharoombackend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @PostMapping("/signup")
    public String member(@PathVariable("id") String id) {
        return "loginForm";
    }

}
