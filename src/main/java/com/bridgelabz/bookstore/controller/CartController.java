package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.dto.CartDTO;
import com.bridgelabz.bookstore.dto.ResponseDTO;
import com.bridgelabz.bookstore.model.Cart;
import com.bridgelabz.bookstore.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CartController {
    @Autowired
    ICartService iCartService;

    @PostMapping("/insertProductInCart")
    public ResponseEntity<ResponseDTO> insertProductInCart(@RequestBody CartDTO cartDTO){
        return iCartService.insertProductInCart(cartDTO);
    }

    @GetMapping("/getAllCart")
    public ResponseEntity<ResponseDTO> getAllCard(){
        return iCartService.getAllCard();
    }

    @GetMapping("/getCartById/{id}")
    public ResponseEntity<ResponseDTO> getById(@PathVariable int id){
        return iCartService.getById(id);
    }

    @DeleteMapping("/deleteCartById/{id}")
    public ResponseEntity<ResponseDTO> deleteCardById(@PathVariable int id){
        return iCartService.deleteCardById(id);
    }

    @PutMapping("/updateCartById/{id}")
    public ResponseEntity<ResponseDTO> updateCardById(@PathVariable int id ,
                                                      @RequestBody CartDTO cartDTO){
        return iCartService.updateCardById(id,cartDTO);
    }

    @PutMapping("/updateQuantityById/{id}")
    public ResponseEntity<ResponseDTO> updateQuantity(@PathVariable int id,
                                                      @RequestParam(value = "quantity") int quantity){
        return iCartService.updateQuantity(id,quantity);
    }
}
