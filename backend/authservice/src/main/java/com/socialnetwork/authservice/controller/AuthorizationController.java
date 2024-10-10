package com.socialnetwork.authservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/resource")
public class AuthorizationController {
    @GetMapping
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Here is your resource");
    }
}
