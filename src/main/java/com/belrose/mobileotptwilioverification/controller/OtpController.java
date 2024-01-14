package com.belrose.mobileotptwilioverification.controller;

import com.belrose.mobileotptwilioverification.dto.OtpRequest;
import com.belrose.mobileotptwilioverification.dto.OtpResponse;
import com.belrose.mobileotptwilioverification.dto.OtpValidateRequest;
import com.belrose.mobileotptwilioverification.service.OtpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/otp")
@Slf4j
public class OtpController {

    @Autowired
    private OtpService otpService;

    @GetMapping("/process")
    public ResponseEntity<String> processSMS(){
        return ResponseEntity.ok().body("Welcome to spring boot application for sending OTP to mobile");
    }

    @PostMapping("/send-otp")
    public ResponseEntity<OtpResponse> sendSMS(@RequestBody OtpRequest otpRequest){
        log.info("OtpController:sendSMS::otpRequest {}",otpRequest);
        return ResponseEntity.ok().body(otpService.sendSMS(otpRequest));
    }

    @PostMapping("/validate-otp")
    public ResponseEntity<String> validateOtp(@RequestBody OtpValidateRequest otpValidateRequest){
        log.info("OtpController:validateOtp::otpValidateRequest {}",otpValidateRequest);
        return ResponseEntity.ok().body(otpService.validateOtp(otpValidateRequest));
    }
}
