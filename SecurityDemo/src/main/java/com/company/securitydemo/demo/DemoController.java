package com.company.securitydemo.demo;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/demo")
public class DemoController {

    @GetMapping
    public ResponseEntity<String> greetings(){
        return ResponseEntity.ok("Hello there!");
    }
}
