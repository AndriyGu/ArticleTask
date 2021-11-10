package com.test.ArticleTestTask.controller;

import com.test.ArticleTestTask.exception.RegistrationException;
import com.test.ArticleTestTask.model.DTO.UserDTO;
import com.test.ArticleTestTask.service.RegistrationService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/registration")
public class RegistrationController {


    @Autowired
    RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    //method for registration
    @Operation(summary = "method for registration (role can be equals only 'user')")
    @PostMapping
    ResponseEntity<?> registration(@RequestBody UserDTO userDTO) {

        Map<String, String> res = new HashMap<>();

        try {
            String result = registrationService.registration(userDTO);
            res.put("message", result);
            return ResponseEntity.status(201).body(res);
        } catch (RegistrationException e) {
            res.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(res);
        }

    }
}