package com.greatkapital.messageservice.service;

import com.greatkapital.messageservice.config.EncrypctionServiceConfiguration;
import com.greatkapital.messageservice.dao.adaptor.MessageDetailsDBAdaptor;
import com.greatkapital.messageservice.dto.AcceptMessageResponseDto;
import com.greatkapital.messageservice.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;
//todo reference
@Service
@Slf4j
public class MessageService {

    @Autowired
    private MessageDetailsDBAdaptor messageDetailsDBAdaptor;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    EncrypctionServiceConfiguration encrypctionServiceConfiguration;

    @Value("${include.id:false}")
    private boolean includeIdInResponse;

    @Value("${save.original.message:true}")
    private boolean saveOriginalMessage;

    public ResponseEntity<?> acceptMessage(AcceptMessageRequestDto request) {
        try {
            String ENCRYPTION_SERVICE_URL = encrypctionServiceConfiguration.getEncryptPath();
            HttpEntity<?> encryptRequest = populateHeadersAndPayload(request);
            ResponseEntity<AcceptMessageResponseDto> encryptResponse = restTemplate.postForEntity(ENCRYPTION_SERVICE_URL, encryptRequest, AcceptMessageResponseDto.class);
            String encryptedMessage = Objects.requireNonNull(encryptResponse.getBody()).getEncryptedMessage();
            Message message = new Message();
            if(saveOriginalMessage) message.setOriginalMessage(request.getMessage());
            message.setEncryptedMessage(encryptedMessage);
            String uid = messageDetailsDBAdaptor.saveMessage(message).getId();
            if(encryptResponse.getStatusCode().is2xxSuccessful()) encryptResponse.getBody().setStatus("SUCCESS");
            if(includeIdInResponse) encryptResponse.getBody().setMessageId(uid);
            return encryptResponse;
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            return handleHttpException("acceptMessage", e);
        } catch (Exception e) {
            return handleException("acceptMessege", e);
        }
    }

    public ResponseEntity<?> getMessage(String id) {
        Optional<Message> messageOptional = messageDetailsDBAdaptor.getMessage(id);
        if (messageOptional.isPresent()) {
            Message message = messageOptional.get();
            return ResponseEntity.
                    status(200).
                    body(GetMessageRequestDto.builder().
                            messageId(message.getId()).
                            encryptedMessage(message.getEncryptedMessage()).build());
        } else {
            HashMap<String, String> err = new HashMap<>();
            err.put("error","Message not found");
            err.put("status", "404");
            return ResponseEntity.status(404).body(err);
        }
    }

    private <T> HttpEntity<?> populateHeadersAndPayload(T request) {
        HttpHeaders httpHeaders = new HttpHeaders();
        return (HttpEntity<?>) new HttpEntity(request, httpHeaders);
    }

    private ResponseEntity<Object> handleException(String methodName, Exception e) {
        log.error(methodName + " - error occurred with message {}", e.getMessage(), e);
        return ResponseEntity.status(500)
                .body("error occurred: " + e.getMessage());
    }

    private ResponseEntity<Object> handleHttpException(String methodName, HttpStatusCodeException e) {
        log.error(methodName + " - Http error occurred whith stats code : {} and response {}", e.getStatusCode(), e.getResponseBodyAsString(), e);
        return ResponseEntity.status(e.getStatusCode())
                .body("HTTP error occurred: " + e.getStatusCode() + " - " + e.getStatusText());
    }
}
