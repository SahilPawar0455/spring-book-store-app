package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.dto.ResponseDTO;
import org.springframework.http.ResponseEntity;

public interface IBookService {
    ResponseEntity<ResponseDTO> insertBook(BookDTO bookDTO);
    ResponseEntity<ResponseDTO> getAllBook();
    ResponseEntity<ResponseDTO> getById(int id);
    ResponseEntity<ResponseDTO> deleteBookByBookName(String bookName);
    ResponseEntity<ResponseDTO> gettingBookByBookName(String bookName);
    ResponseEntity<ResponseDTO> updateBookByBookId(int id,BookDTO bookDTO);
    ResponseEntity<ResponseDTO> sortingBookPriceInDescendingOrder();
    ResponseEntity<ResponseDTO> sortingBookPriceInAscendingOrder();
    ResponseEntity<ResponseDTO> updateQuantity(String bookName,int quantity);

}
