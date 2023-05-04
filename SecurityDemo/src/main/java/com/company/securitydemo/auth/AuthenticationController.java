package com.company.securitydemo.auth;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/jwt")
public class AuthenticationController {

    @PostMapping("/register")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("Hi");
    }
}
