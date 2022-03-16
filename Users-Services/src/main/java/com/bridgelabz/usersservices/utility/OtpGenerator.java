package com.bridgelabz.usersservices.utility;

import org.springframework.stereotype.Component;

@Component
public class OtpGenerator {

    public  int generateOTP(){
        int otp   =(int) (Math.random()*900000)+100000;
        return otp;
    }
}
