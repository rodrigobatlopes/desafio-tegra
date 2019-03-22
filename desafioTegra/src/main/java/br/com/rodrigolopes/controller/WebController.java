package br.com.rodrigolopes.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {
    @GetMapping(path = "/hi", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String>  Hi() {
        System.out.println("Inside WebController.java");
        return ResponseEntity.ok("Hi");
    }
}