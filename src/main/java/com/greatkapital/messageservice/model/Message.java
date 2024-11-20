package com.greatkapital.messageservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
//todo - this is entity for creating table , just for reference
@Entity
@Data
public class Message {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;
    private String originalMessage;
    private String encryptedMessage;
}
