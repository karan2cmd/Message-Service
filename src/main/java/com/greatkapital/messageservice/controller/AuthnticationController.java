package com.greatkapital.messageservice.controller;

import com.greatkapital.messageservice.dao.adaptor.MessageDetailsDBAdaptor;
import com.greatkapital.messageservice.dto.SignupDto;
import com.greatkapital.messageservice.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/message")
public class AuthnticationController {
    @Autowired
    private MessageDetailsDBAdaptor messageDetailsDBAdaptor;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AuthenticationService authenticationService;


    @PostMapping
    public ResponseEntity<?> signup(SignupDto request) {
        ResponseEntity<?> response = authenticationService.signUp(request);
        //if true process berjna hai
        return response;
    }
}

