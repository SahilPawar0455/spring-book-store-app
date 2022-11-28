package com.bridgelabz.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {

    private int bookId;

    @NotEmpty(message = "Book name is mandatory")
    @Pattern(regexp = "[A-Z]{3,}",message = "Book Name in Capital latter")
    private String bookName;

    @NotEmpty(message = "Author name is mandatory")
    private String authorName;

    @NotEmpty(message = "Book Description is mandatory")
    private String bookDescription;

    @NotEmpty(message = "Book Img is mandatory")
    private String bookImg;

    private long price;

    private long quantity;
}
