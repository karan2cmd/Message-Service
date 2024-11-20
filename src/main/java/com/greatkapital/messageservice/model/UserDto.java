package com.greatkapital.messageservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import org.springframework.context.annotation.Primary;

@Entity
@Data
@Builder
public class UserDto {
    @Id
    private String userName;
    private String password;
    private String personalInfo;

}
