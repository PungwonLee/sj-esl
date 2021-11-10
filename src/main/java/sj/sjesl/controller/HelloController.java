package sj.sjesl.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sj.sjesl.config.jwt.JwtTokenProvider;

import java.security.Principal;

@Controller
public class HelloController {
    @GetMapping("/helloo")
    public String hello() {
        return "hello";
    }

//    @GetMapping("/username")
//    @ResponseBody
//    public String currentUserName(Authentication authentication) {
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        return userDetails.getUsername();
//    }\
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("token")
    public String token(){
        return "<h1>token</h1>";
    }


}


