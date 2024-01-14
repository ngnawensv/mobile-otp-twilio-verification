package com.belrose.mobileotptwilioverification;

import com.belrose.mobileotptwilioverification.config.TwilioConfig;
import com.twilio.Twilio;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MobileOtpTwilioVerificationApplication {
	@Autowired
	private TwilioConfig twilioConfig;
	@PostConstruct
	public void setup(){
		Twilio.init(twilioConfig.getAccountSId(),twilioConfig.getAuthToken());
	}

	public static void main(String[] args) {
		SpringApplication.run(MobileOtpTwilioVerificationApplication.class, args);
	}

}
