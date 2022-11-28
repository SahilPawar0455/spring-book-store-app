package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.dto.ResponseDTO;
import com.bridgelabz.bookstore.exception.Exception;
import com.bridgelabz.bookstore.model.Book;
import com.bridgelabz.bookstore.repository.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IBookService{
    @Autowired
    IBookRepository iBookRepository;

    @Override
    public ResponseEntity<ResponseDTO> insertBook(BookDTO bookDTO){
        if (iBookRepository.findBookByBookName(bookDTO.getBookName()) == null) {
            Book book = new Book(bookDTO);
            iBookRepository.save(book);
            ResponseDTO responseDTO = new ResponseDTO("Successfully insert the book", book);
            return new ResponseEntity(responseDTO, HttpStatus.CREATED);
        }else {
            throw new Exception("Book is Already Present");
        }
    }

    @Override
    public ResponseEntity<ResponseDTO> getAllBook(){
        List<Book> bookList = iBookRepository.findAll();
        if (bookList.isEmpty()){
            throw new Exception("Book is not Present");
        }else {
            ResponseDTO responseDTO = new ResponseDTO("Getting all Book", bookList);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<ResponseDTO> getById(int id){
        Optional<Book> book = iBookRepository.findById(id);
        if (book.isEmpty()){
            throw new Exception("Book is not Present");
        }else {
            ResponseDTO responseDTO = new ResponseDTO("Getting Book", book);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<ResponseDTO> deleteBookByBookName(String bookName){
        Book book = iBookRepository.findBookByBookName(bookName);
        if (book!= null){
            iBookRepository.delete(book);
            ResponseDTO responseDTO = new ResponseDTO("Successfully Delete the Book",book);
            return new  ResponseEntity<>(responseDTO,HttpStatus.OK);
        }else {
            throw new Exception("Book is not Present");
        }
    }

    @Override
    public ResponseEntity<ResponseDTO> gettingBookByBookName(String bookName){
        if (iBookRepository.findBookByBookName(bookName)!= null){
            ResponseDTO responseDTO = new ResponseDTO("Successfully Getting the Book",iBookRepository.findBookByBookName(bookName));
            return new  ResponseEntity(responseDTO,HttpStatus.OK);
        }else {
            throw new Exception("Book is not Present");
        }
    }

    @Override
    public ResponseEntity<ResponseDTO> updateBookByBookId(int id,BookDTO bookDTO){
        if (iBookRepository.findById(id).isEmpty()){
            throw new Exception("Book is not Present");
        }else {
            Book book = new Book(bookDTO);
            book.setBookId(iBookRepository.findById(id).get().getBookId());
            iBookRepository.save(book);
            ResponseDTO responseDTO = new ResponseDTO("Successfully Update the Book ",book);
            return new ResponseEntity<>(responseDTO,HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<ResponseDTO> sortingBookPriceInDescendingOrder(){
        List<Book> descendingOrder = iBookRepository.sortingBookPriceInDescendingOrder();
        if (descendingOrder.isEmpty()){
            throw new Exception("Book is Not Present");
        }else {
            ResponseDTO responseDTO = new ResponseDTO("Successfully Sorting the Book in Descending Order",descendingOrder);
            return new ResponseEntity<>(responseDTO,HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<ResponseDTO> sortingBookPriceInAscendingOrder(){
        List<Book> ascendingOrder = iBookRepository.sortingBookPriceInAscendingOrder();
        if (ascendingOrder.isEmpty()){
            throw new Exception("Book is Not Present");
        }else {
            ResponseDTO responseDTO = new ResponseDTO("Successfully Sorting the Book in Descending Order",ascendingOrder);
            return new ResponseEntity<>(responseDTO,HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<ResponseDTO> updateQuantity(String bookName,int quantity){
        Book book = iBookRepository.findBookByBookName(bookName);
        if (book != null){
            book.setQuantity(quantity);
            book.setBookId(iBookRepository.findBookByBookName(bookName).getBookId());
            iBookRepository.save(book);
            ResponseDTO responseDTO = new ResponseDTO("Update the Quantity ",book);
            return new ResponseEntity<>(responseDTO,HttpStatus.OK);
        }else {
            throw new Exception("Book is not Found");
        }
    }
}
