package com.bridgelabz.usersservices.service;


import com.bridgelabz.usersservices.Model.User;
import com.bridgelabz.usersservices.dto.LoginDto;
import com.bridgelabz.usersservices.dto.UserRequest;

import javax.mail.MessagingException;
import java.util.List;

public interface UserService {
    String userLogin(LoginDto loginDto);

    User registerUser(UserRequest userRequest) throws MessagingException;
    String verifyEmail(int otp) ;


    String verifyUsingToken(String token);

    List<User> getAll();

    User getUserById(Long id);

    User updateUserById(Long id, UserRequest userRequest);


    void deleteUserById(Long id);

    String forgotPassword(String emailId) throws MessagingException;

    String resetPassword(int otp, String newPassword);
}
