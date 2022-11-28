package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.dto.ResponseDTO;
import com.bridgelabz.bookstore.dto.UserDTO;
import com.bridgelabz.bookstore.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;

@RestController
public class UserController {
    @Autowired
    IUserService iUserService;

    @PostMapping("/registration")
    public ResponseEntity<ResponseDTO> registration(@Valid @RequestBody UserDTO userDTO) throws MessagingException {
        return iUserService.registration(userDTO);
    }

    @GetMapping("/getAllUser")
    public ResponseEntity<ResponseDTO> getAllUser(){
        return iUserService.getAllUser();
    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<ResponseDTO> getUserById(@PathVariable int id){
        return  iUserService.getUserById(id);
    }

    @GetMapping("/getUserByToken")
    public ResponseEntity<ResponseDTO> getUserByToken(@RequestParam(value = "token") String token){
        return iUserService.getUserByToken(token);
    }


    @GetMapping("getUserByEmail/{email}")
    public ResponseEntity<ResponseDTO> getUserByEmail(@PathVariable String email){
        return iUserService.getUserByEmail(email);
    }

    @PutMapping("changePassword/{email}")
    public ResponseEntity<ResponseDTO> changePassword(@PathVariable String email,
                                                      @Valid @RequestParam(value = "password") String password){
        return iUserService.changePassword(email,password);
    }

    @PutMapping("/changePasswordByToken")
    public ResponseEntity<ResponseDTO> changePasswordByToken(@RequestParam(value = "token")String token,
                                                      @Valid @RequestParam(value = "password")String password){
        return iUserService.changePasswordByToken(token,password);
    }

    @PutMapping("/updateUserInformation/{email}")
    public ResponseEntity<ResponseDTO> updateUserInformation(@PathVariable String email,
                                                             @Valid @RequestBody UserDTO userDTO){
        return iUserService.updateUserInformation(email,userDTO);
    }

    @PutMapping("/updateUserInformationByToken")
    public ResponseEntity<ResponseDTO> updateUserInformationByToken(@RequestParam(value = "token")String token,
                                                                    @Valid @RequestBody UserDTO userDTO){
        return iUserService.updateUserInformationByToken(token,userDTO);
    }

    @GetMapping("/login/{email}")
    public ResponseEntity<ResponseDTO> login(@PathVariable String email,
                                             @RequestParam(value = "password") String password){
        return iUserService.login(email,password);
    }
}
