package com.bridgelabz.bookstore.model;

import com.bridgelabz.bookstore.dto.BookDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;
    private String bookName;
    private String authorName;
    private String bookDescription;
    private String bookImg;
    private long price;
    private long quantity;

    public Book(BookDTO bookDTO) {
        this.bookName = bookDTO.getBookName();
        this.authorName = bookDTO.getAuthorName();
        this.bookDescription = bookDTO.getBookDescription();
        this.bookImg = bookDTO.getBookImg();
        this.price = bookDTO.getPrice();
        this.quantity = bookDTO.getQuantity();
    }

    public Book(int id, BookDTO bookDTO) {
        this.bookId = id;
        this.bookName = bookDTO.getBookName();
        this.authorName = bookDTO.getAuthorName();
        this.bookDescription = bookDTO.getBookDescription();
        this.bookImg = bookDTO.getBookImg();
        this.price = bookDTO.getPrice();
        this.quantity = bookDTO.getQuantity();
    }
}
