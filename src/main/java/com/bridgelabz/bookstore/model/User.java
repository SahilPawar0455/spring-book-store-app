package com.bridgelabz.bookstore.model;

import com.bridgelabz.bookstore.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String dateOfBirth;
    private String password;

    public User(UserDTO userDTO) {
        this.firstName = userDTO.getFirstName();
        this.lastName = userDTO.getLastName();
        this.email = userDTO.getEmail();
        this.address = userDTO.getAddress();
        this.dateOfBirth = userDTO.getDateOfBirth();
        this.password = userDTO.getPassword();
    }

    public User(String email, UserDTO userDTO) {
        this.userId = userDTO.getUserId();
        this.firstName = userDTO.getFirstName();
        this.lastName = userDTO.getLastName();
        this.address = userDTO.getAddress();
        this.email = email;
        this.dateOfBirth = userDTO.getDateOfBirth();
        this.password = userDTO.getPassword();
    }
}
