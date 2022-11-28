package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.CartDTO;
import com.bridgelabz.bookstore.dto.ResponseDTO;
import com.bridgelabz.bookstore.model.Cart;
import org.springframework.http.ResponseEntity;

public interface ICartService {
    ResponseEntity<ResponseDTO> insertProductInCart(CartDTO cartDTO);
    ResponseEntity<ResponseDTO> getAllCard();
    ResponseEntity<ResponseDTO> getById(int id);
    ResponseEntity<ResponseDTO> deleteCardById(int id);
    ResponseEntity<ResponseDTO> updateCardById(int id , CartDTO cartDTO);
    ResponseEntity<ResponseDTO> updateQuantity(int id,int quantity);
}
