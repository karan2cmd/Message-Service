package com.greatkapital.messageservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = EncrypctionServiceConfiguration.PREFIX)
public class EncrypctionServiceConfiguration {
    public static final String PREFIX = "com.greatkapital.encryption.service";
    private String host = "http://localhost:8082";
    private String encrypt = "/api/encrypt";

    public String getEncryptPath(){
        return getHost()+encrypt;
    }
}
