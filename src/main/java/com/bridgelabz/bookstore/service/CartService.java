package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.CartDTO;
import com.bridgelabz.bookstore.dto.ResponseDTO;
import com.bridgelabz.bookstore.exception.Exception;
import com.bridgelabz.bookstore.model.Book;
import com.bridgelabz.bookstore.model.Cart;
import com.bridgelabz.bookstore.model.User;
import com.bridgelabz.bookstore.repository.IBookRepository;
import com.bridgelabz.bookstore.repository.ICartRepository;
import com.bridgelabz.bookstore.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService implements ICartService{
    @Autowired
    ICartRepository iCartRepository;
    @Autowired
    IUserRepository iUserRepository;
    @Autowired
    IBookRepository iBookRepository;

    @Override
    public ResponseEntity<ResponseDTO> insertProductInCart(CartDTO cartDTO){
        Optional<User> user = iUserRepository.findById(cartDTO.getUserId());
        Optional<Book> book = iBookRepository.findById(cartDTO.getBookID());
        if (user.isPresent()){
            if (book.isPresent()){
                Cart cart = new Cart(user.get(),book.get(),cartDTO.getQuantity());
                iCartRepository.save(cart);
                ResponseDTO responseDTO = new ResponseDTO("Successfully add in cart",cart);
                return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
            }
            throw new Exception("Book is Not Present");
        }
        throw new Exception("User is Not Present");
    }

    @Override
    public ResponseEntity<ResponseDTO> getAllCard(){
        List<Cart> cartList = iCartRepository.findAll();
        if (cartList.isEmpty()){
            throw new Exception("Cart is Not Present");
        }else {
            ResponseDTO responseDTO = new ResponseDTO("Getting all Carts Information", cartList);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<ResponseDTO> getById(int id){
        Optional<Cart> card = iCartRepository.findById(id);
        if (card.isPresent()){
            ResponseDTO responseDTO = new ResponseDTO("Getting  Cart Information",card);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }else {
            throw new Exception("Cart is Not Present");
        }
    }

    @Override
    public ResponseEntity<ResponseDTO> deleteCardById(int id){
        Optional<Cart> card = iCartRepository.findById(id);
       if (card.isEmpty()) {
           throw new Exception("Cart is Not Present");
       }else {
           ResponseDTO responseDTO = new ResponseDTO("Delete Cart Information", card);
           iCartRepository.deleteById(id);
           return new ResponseEntity<>(responseDTO,HttpStatus.OK);
       }
    }

    @Override
    public ResponseEntity<ResponseDTO> updateCardById(int id , CartDTO cartDTO){
        Optional<Cart> card = iCartRepository.findById(id);
        if (card.isPresent()){
            Optional<User> user = iUserRepository.findById(cartDTO.getUserId());
            Optional<Book> book = iBookRepository.findById(cartDTO.getBookID());
            if (user.isPresent()){
                if (book.isPresent()){
                    Cart cart = new Cart(id,user.get(),book.get(), cartDTO.getQuantity());
                    iCartRepository.save(cart);
                    ResponseDTO responseDTO = new ResponseDTO("Successfully Update in cart",cart);
                    return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
                }
                throw new Exception("Book is Not Present");
            }
            throw new Exception("User is Not Present");
        }else {
            throw new Exception("Cart is Not Present");
        }
    }

    @Override
    public ResponseEntity<ResponseDTO> updateQuantity(int id,int quantity){
        Optional<Cart> card = iCartRepository.findById(id);
        if (card.isPresent()){
            card.get().setQuantity(quantity);
            card.get().setCartId(iCartRepository.findById(id).get().getCartId());
            iCartRepository.save(card.get());
            ResponseDTO responseDTO = new ResponseDTO("Successfully Update the Quantity in cart",card.get());
            return new ResponseEntity<>(responseDTO,HttpStatus.OK);
        }
        throw new Exception("Cart is Not Present");
    }
}
