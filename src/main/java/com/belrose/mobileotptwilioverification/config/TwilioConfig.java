package com.belrose.mobileotptwilioverification.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "twilio")
@Slf4j
@Data
public class TwilioConfig {
    private String accountSId;
    private String authToken;
    private String phoneNumber;
}
