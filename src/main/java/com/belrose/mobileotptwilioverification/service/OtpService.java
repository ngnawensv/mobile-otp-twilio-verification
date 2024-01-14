package com.belrose.mobileotptwilioverification.service;

import com.belrose.mobileotptwilioverification.dto.OtpRequest;
import com.belrose.mobileotptwilioverification.dto.OtpResponse;
import com.belrose.mobileotptwilioverification.dto.OtpValidateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


public interface OtpService {
    OtpResponse sendSMS(OtpRequest phoneNumber);

    String validateOtp(OtpValidateRequest otpValidateRequest);
}