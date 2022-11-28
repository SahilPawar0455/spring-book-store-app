package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.Util.ITokenUtil;
import com.bridgelabz.bookstore.dto.LoginDTO;
import com.bridgelabz.bookstore.dto.ResponseDTO;
import com.bridgelabz.bookstore.dto.UserDTO;
import com.bridgelabz.bookstore.exception.Exception;
import com.bridgelabz.bookstore.model.Email;
import com.bridgelabz.bookstore.model.User;
import com.bridgelabz.bookstore.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Service
public class UserService implements IUserService {
    @Autowired
    IUserRepository iUserRepository;
    @Autowired
    IEmailService iEmailService;
    @Autowired
    ITokenUtil iTokenUtil;
    @Autowired
    IUserService iUserService;
    @Override
    public ResponseEntity<ResponseDTO> registration(UserDTO userDTO) {
        User user = new User(userDTO);
        if (iUserRepository.findByEmail(userDTO.getEmail()) == null) {
            iUserRepository.save(user);
            String token = iTokenUtil.createToken(user.getUserId());
            Email email = new Email(userDTO.getEmail(), "Account Sign-up successfully", "Hello  " + userDTO.getFirstName() + " Your Account has been created. "+"And your Token is "+token);
            iEmailService.sendMail(email);
            ResponseDTO responseDTO = new ResponseDTO("Successfully registration is complete ", user);
            return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
        }else {
            ResponseDTO responseDTO = new ResponseDTO("You have already create account ", user);
            return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
        }
    }

    @Override
    public ResponseEntity<ResponseDTO> getUserByToken(String token){
        int id = iTokenUtil.decodeToken(token);
        return iUserService.getUserById(id);
    }
    @Override
    public ResponseEntity<ResponseDTO> getUserById(int id){
        Optional<User> user = iUserRepository.findById(id);
        if (user.isEmpty()){
            throw new Exception("User is Not Found");
        }else {
            ResponseDTO responseDTO = new ResponseDTO("Successfully Getting the  User ", user);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<ResponseDTO> getAllUser(){
        List<User> allUser = iUserRepository.findAll();
        if (allUser.isEmpty()){
            throw new Exception("User is Not Found in Database");
        }else {
            ResponseDTO responseDTO = new ResponseDTO("Successfully Getting the All User ", allUser);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }
    }
    @Override
    public ResponseEntity<ResponseDTO> getUserByEmail(String email){
        if (iUserRepository.findByEmail(email) != null){
            ResponseDTO responseDTO = new ResponseDTO("Successfully Getting the  User ",iUserRepository.findByEmail(email));
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }else {
            throw new Exception("User is Not Found");
        }
    }

    @Override
    public ResponseEntity<ResponseDTO> changePasswordByToken(String token,String password){
       int tokenNumber = iTokenUtil.decodeToken(token);
       Optional<User> user = iUserRepository.findById(tokenNumber);
       if (user.isPresent()){
           return iUserService.changePassword(user.get().getEmail(),password);
       }else {
           throw new Exception("User is Not Found");
       }
    }


    @Override
    public ResponseEntity<ResponseDTO> changePassword(String email,String password){
        if (iUserRepository.findByEmail(email) != null) {
            User user1 = iUserRepository.findByEmail(email);
            user1.setPassword(password);
            user1.setUserId(iUserRepository.findByEmail(email).getUserId());
            iUserRepository.save(user1);
            Email email1 = new Email(user1.getEmail(), "Change Password successfully", "Hello  " + user1.getFirstName() + " Your Password is "+user1.getPassword());
            iEmailService.sendMail(email1);
            ResponseDTO responseDTO = new ResponseDTO("Successfully change password ", user1);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }else {
            throw new Exception("User is Not Found");
        }
    }

    @Override
    public ResponseEntity<ResponseDTO> updateUserInformationByToken(String token,UserDTO userDTO){
        int tokenNumber = iTokenUtil.decodeToken(token);
        Optional<User> user = iUserRepository.findById(tokenNumber);
        if (user.isPresent()){
            return iUserService.updateUserInformation(user.get().getEmail(),userDTO);
        }else {
            throw new Exception("User is Not Found");
        }
    }

    @Override
    public ResponseEntity<ResponseDTO> updateUserInformation(String email,UserDTO userDTO){
        User user1 = iUserRepository.findByEmail(email);
        if (user1 != null){
           User user = new User(email, userDTO);
           user.setUserId(user1.getUserId());
           iUserRepository.save(user);
            Email email1 = new Email(user.getEmail(), "Successfully Update the Information", "Hello  " + user.getFirstName() + " Successfully Update the Information ");
            iEmailService.sendMail(email1);
           ResponseDTO responseDTO = new ResponseDTO("Successfully Update the  User Information ", user);
           return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }else {
            throw new Exception("User is Not Found");
        }
    }

    @Override
    public ResponseEntity<ResponseDTO> login(String email,String password){
            if (iUserRepository.findByEmail(email) != null) {
                if (Objects.equals(iUserRepository.findByEmail(email).getPassword(), password)) {
                    LoginDTO loginDTO = new LoginDTO(email, password);
                    ResponseDTO responseDTO = new ResponseDTO("Successfully Login User Information ", loginDTO);
                    return new ResponseEntity<>(responseDTO, HttpStatus.OK);
                } else
                    throw new Exception("Password is wrong");
            } else {
                throw new Exception("Email is Not Found");
            }
    }
}
