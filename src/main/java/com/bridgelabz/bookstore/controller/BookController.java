package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.dto.ResponseDTO;
import com.bridgelabz.bookstore.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class BookController {
    @Autowired
    IBookService iBookService;

    @PostMapping("/insertBook")
    public ResponseEntity<ResponseDTO> insertBook(@Valid @RequestBody BookDTO bookDTO){
        return iBookService.insertBook(bookDTO);
    }

    @GetMapping("/getAllBook")
    public ResponseEntity<ResponseDTO> getAllBook(){
        return iBookService.getAllBook();
    }

    @GetMapping("/getBookById/{id}")
    public ResponseEntity<ResponseDTO> getById(@PathVariable int id){
        return iBookService.getById(id);
    }

    @DeleteMapping("/deleteBookByBookName/{bookName}")
    public ResponseEntity<ResponseDTO> deleteBookByBookName(@PathVariable String bookName){
        return iBookService.deleteBookByBookName(bookName);
    }

    @GetMapping("/getBookByBookName/{bookName}")
    public ResponseEntity<ResponseDTO> gettingBookByBookName(@PathVariable String bookName){
        return iBookService.gettingBookByBookName(bookName);
    }

    @PutMapping("/updateBookByBookId/{id}")
    public ResponseEntity<ResponseDTO> updateBookByBookId(@PathVariable int id,
                                                          @Valid @RequestBody BookDTO bookDTO){
        return iBookService.updateBookByBookId(id,bookDTO);
    }

    @GetMapping("/sortingBookPriceInDescendingOrder")
    public ResponseEntity<ResponseDTO> sortingBookPriceInDescendingOrder(){
        return iBookService.sortingBookPriceInDescendingOrder();
    }

    @GetMapping("/sortingBookPriceInAscendingOrder")
    public ResponseEntity<ResponseDTO> sortingBookPriceInAscendingOrder(){
        return iBookService.sortingBookPriceInAscendingOrder();
    }

    @PutMapping("/updateQuantity/{bookName}")
    public ResponseEntity<ResponseDTO> updateQuantity(@PathVariable String bookName,
                                                      @RequestParam(value = "quantity") int quantity){
        return iBookService.updateQuantity(bookName,quantity);
    }
}
