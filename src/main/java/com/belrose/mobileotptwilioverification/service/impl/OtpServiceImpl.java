package com.belrose.mobileotptwilioverification.service.impl;

import com.belrose.mobileotptwilioverification.config.TwilioConfig;
import com.belrose.mobileotptwilioverification.dto.OtpRequest;
import com.belrose.mobileotptwilioverification.dto.OtpResponse;
import com.belrose.mobileotptwilioverification.dto.OtpStatusEnum;
import com.belrose.mobileotptwilioverification.dto.OtpValidateRequest;
import com.belrose.mobileotptwilioverification.service.OtpService;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

@Service
@Slf4j
public class OtpServiceImpl implements OtpService {
    private final TwilioConfig twilioConfig;
    Map<String,String> otpMap = new HashMap<>();

    public OtpServiceImpl(TwilioConfig twilioConfig) {
        this.twilioConfig = twilioConfig;
    }


    @Override
    public OtpResponse sendSMS(OtpRequest otpRequest) {
        OtpResponse otpResponse =null;
        try{
            PhoneNumber to = new PhoneNumber(otpRequest.getPhoneNumber());
            PhoneNumber from = new PhoneNumber(twilioConfig.getPhoneNumber());

            String otp = generateOtp();

            String otpMessage = String.format("Your One Time Password is : %s",otp);
            Message message = Message.creator(to,from,otpMessage).create();
            otpMap.put(otpRequest.getUsername(),otp);
            otpResponse = new OtpResponse(OtpStatusEnum.DELIVERED,otpMessage);
        } catch (Exception e) {
            log.error(e.getMessage());
            otpResponse = new OtpResponse(OtpStatusEnum.FAILED,e.getMessage());
        }
        return otpResponse;
    }

    @Override
    public String validateOtp(OtpValidateRequest otpValidateRequest) {
        String resp = null;
        Set<String> keys = otpMap.keySet();
        String username = null;
        for (String key:keys){
            username = key;
            if(otpValidateRequest!=null && otpValidateRequest.getUsername().equals(username)){
                otpMap.remove(username,otpValidateRequest.getOtpNumber());
                resp= "OTP is valid";
            }else {
                resp= "OTP is invalid";
            }
        }
        return resp;
    }

//    private String generateOtp(){
//        int otp = (int) (Math.random()*100_000);
//        return String.format("%06d",otp);
//    }

    private String generateOtp(){
        return new DecimalFormat("000_000").format(new Random().nextInt(999_999));
    }
}
