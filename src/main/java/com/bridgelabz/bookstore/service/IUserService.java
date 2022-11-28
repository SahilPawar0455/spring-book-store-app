package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.ResponseDTO;
import com.bridgelabz.bookstore.dto.UserDTO;
import org.springframework.http.ResponseEntity;

import javax.mail.MessagingException;

public interface IUserService {
    ResponseEntity<ResponseDTO> registration(UserDTO userDTO) throws MessagingException;
    ResponseEntity<ResponseDTO> getAllUser();
    ResponseEntity<ResponseDTO> getUserById(int id);
    ResponseEntity<ResponseDTO> getUserByEmail(String email);
    ResponseEntity<ResponseDTO> changePassword(String email,String password);
    ResponseEntity<ResponseDTO> updateUserInformation(String email,UserDTO userDTO);
    ResponseEntity<ResponseDTO> login(String email,String password);
    ResponseEntity<ResponseDTO> getUserByToken(String token);
    ResponseEntity<ResponseDTO> changePasswordByToken(String token,String password);
    ResponseEntity<ResponseDTO> updateUserInformationByToken(String token,UserDTO userDTO);
}
