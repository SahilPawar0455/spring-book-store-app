package com.bridgelabz.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartId;

    //One To One because one cart has only one user
    @OneToOne
    @JoinColumn(name = "userId")
    private User userId;

    //one to many because one cart has many bookId
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book bookID;
    private int quantity;

    public Cart(User userId, Book bookID, int quantity) {
        this.userId = userId;
        this.bookID = bookID;
        this.quantity = quantity;
    }
}
