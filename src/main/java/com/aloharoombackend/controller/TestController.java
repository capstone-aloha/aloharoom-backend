package com.aloharoombackend.controller;

import com.aloharoombackend.Repository.UserRepository;
import com.aloharoombackend.auth.PrincipalDetails;
import com.aloharoombackend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class TestController {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/loginForm")
    public String loginForm() {
        return "loginForm";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute User user) {
        user.setRole("ROLE_USER");
        String rawPassword = user.getPassword(); //입력받은 pw
        String encPassword = bCryptPasswordEncoder.encode(rawPassword); //인코딩한 pw
        user.setPassword(encPassword);

        //DB에 잘 저장되지만, PW가 암호화 되어있지 않기에 시큐리티로 로그인 불가 =>
        //bCryptPasswordEncoder로 인코딩해서 시큐리티로 로그인 가능
        userRepository.save(user);
        return "redirect:/loginForm";
    }

    @GetMapping("/loginUser")
    @ResponseBody
    public String loginUser(@AuthenticationPrincipal PrincipalDetails userDetails) {
        System.out.println("userDetails: " + userDetails.getUser().getUsername());
        return userDetails.getUser().getUsername();
    }
}
