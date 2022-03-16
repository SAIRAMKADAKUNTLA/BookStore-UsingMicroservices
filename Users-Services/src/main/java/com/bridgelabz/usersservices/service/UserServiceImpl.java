package com.bridgelabz.usersservices.service;


import com.bridgelabz.usersservices.Model.User;
import com.bridgelabz.usersservices.dto.LoginDto;
import com.bridgelabz.usersservices.dto.UserRequest;
import com.bridgelabz.usersservices.exception.BookStoreExceptions;
import com.bridgelabz.usersservices.repository.UserRepository;
import com.bridgelabz.usersservices.utility.JwtToken;
import com.bridgelabz.usersservices.utility.MailUtility;
import com.bridgelabz.usersservices.utility.OtpGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private JwtToken jwtToken;

    @Autowired
    private MailUtility mailUtility;
    @Autowired
    private OtpGenerator otpGenerator;

    int emailotp;
    String jToken;

    @Override
    public String userLogin(LoginDto loginDto) {
        String userByEmail=userRepo.getEmail(loginDto.getEmail());
        String userByPassword=userRepo.getPassword(loginDto.getEmail());
        log.info(userByEmail);
        log.info(userByPassword);
        boolean username=loginDto.getEmail().equals(userByEmail);
        if(!username){
            return "Email is not valid";
        }
        boolean password=bCryptPasswordEncoder.matches(loginDto.getPassword(),userByPassword);
        log.info(userByPassword);
        log.info(loginDto.getPassword());
        if(!password){
            return "Invalid Password!!!";
        }
        jToken=jwtToken.createToken(userRepo.getId(loginDto.getEmail()));
        log.info("token is: "+jToken);
        String message="user logged in successfully and token is :"+jToken;
        return  message;
    }

    @Override
    public User registerUser(UserRequest userRequest) throws MessagingException {
        User user=new User(userRequest);
        user.setPassword(bCryptPasswordEncoder.encode(userRequest.password));
        emailotp =otpGenerator.generateOTP();
        mailUtility.sendOtp(emailotp);
        return userRepo.save(user);
    }

    @Override
    public String verifyEmail(int otp) {
        if(otp == emailotp){
            return "YOUR EMAIL IS VERIFIED!!!";
        }
        else{
            return "YOUR EMAIL IS NOT VERIFIED!!!";
        }
    }

    @Override
    public String verifyUsingToken(String token) {
        if(token.equals(jToken)){
            return "user is verified by token";
        }
        else
        {
            return "user is not verified";
        }
    }

    @Override
    public List<User> getAll() {
        return userRepo.findAll() ;
    }

    @Override
    public User getUserById(Long id) {
        return userRepo.findById(id).orElseThrow(()->new BookStoreExceptions("not found with "+id));
    }

    @Override
    public User updateUserById(Long id, UserRequest userRequest) {
        User userModel=this.getUserById(id);
        userModel.updateUser(userRequest);
        return userRepo.save(userModel) ;
    }

    @Override
    public void deleteUserById(Long id) {
        User userModel=this.getUserById(id);
        userRepo.delete(userModel);
    }

    @Override
    public String forgotPassword(String emailId) throws MessagingException {
        String userByEmail=userRepo.getEmail(emailId);
        Long userId=userRepo.getId(emailId);
        boolean email=emailId.equals(userByEmail);
        if(!email){
            return "not found any user with this email id";
        }
        else{
            emailotp=otpGenerator.generateOTP();
            jToken=jwtToken.createToken(userId);
            mailUtility.sendOtpTo(emailotp,jToken,emailId);
            return "Otp send Successfully";
        }

    }

    @Override
    public String resetPassword(int otp, String newPassword) {
      if(otp != emailotp){
          return "incorrect otp entered";
      }
      else{
          User userModel=null;
          userModel.setPassword(newPassword);
          userRepo.save(userModel);
          return "password changed  successfully";
      }
    }


}
