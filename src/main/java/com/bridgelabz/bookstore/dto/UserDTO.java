package com.bridgelabz.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private int userId;

    @Pattern(regexp = "^[A-Z]{1}[A-Za-z]{2,}$", message = "User First Name Invalid")
    private String firstName;

    @Pattern(regexp = "^[A-Z]{1}[A-Za-z]{2,}$", message = "User Last Name Invalid")
    private String lastName;

   // @Pattern(regexp = "^[a-zA-Z\\\\d+_.-]+@[a-zA-Z]+.[a-zA-z]{2,}", message = "User email Invalid")
   // "^[\\w+-]+(\\.[\\w-]+)*@[^_\\W]+(\\.[^_\\W]+)?(?=(\\.[^_\\W]{3,}$|\\.[a-zA-Z]{2}$)).*$"
    private String email;

    @NotEmpty(message = "Person address is mandatory")
    private String address;

    @NotEmpty(message = "Person Date of Birth is mandatory")
    private String dateOfBirth;

    @Pattern(regexp = "^[A-Z]{1}[A-Za-z0-9]{3,}",message = "User Password Invalid")
    private String password;
}
