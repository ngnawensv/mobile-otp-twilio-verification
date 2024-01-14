package com.belrose.mobileotptwilioverification.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OtpValidateRequest {
    private String username;
    private  String  otpNumber;
}
