package com.bridgelabz.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "OrderDetail")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    private LocalDate date = LocalDate.now();
    private int price;
    private int quantity;
    private String address;
    @OneToOne
    @JoinColumn(name = "user_Id")
    private User userId;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book bookId;
    private boolean cancel;

    public Order(User userId, Book bookId, int quantity, String address,boolean cancel) {
        this.quantity = quantity;
        this.address = address;
        this.userId = userId;
        this.bookId = bookId;
        this.cancel = cancel;
    }
}
