package com.greatkapital.messageservice.controller;

import com.greatkapital.messageservice.dto.AcceptMessageRequestDto;
import com.greatkapital.messageservice.dao.adaptor.MessageDetailsDBAdaptor;
import com.greatkapital.messageservice.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/message")
public class MessageController {
    @Autowired
    private MessageDetailsDBAdaptor messageDetailsDBAdaptor;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MessageService messageService;
    @PostMapping
    public ResponseEntity<?> acceptMessage(@RequestBody AcceptMessageRequestDto request) {
        ResponseEntity<?> response = messageService.acceptMessage(request);
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMessage(@PathVariable String id) {
        ResponseEntity<?> response = messageService.getMessage(id);
        return response;
    }
}

