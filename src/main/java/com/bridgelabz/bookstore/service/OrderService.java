package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.OrderDTO;
import com.bridgelabz.bookstore.dto.ResponseDTO;
import com.bridgelabz.bookstore.exception.Exception;
import com.bridgelabz.bookstore.model.Book;
import com.bridgelabz.bookstore.model.Cart;
import com.bridgelabz.bookstore.model.Order;
import com.bridgelabz.bookstore.model.User;
import com.bridgelabz.bookstore.repository.IBookRepository;
import com.bridgelabz.bookstore.repository.ICartRepository;
import com.bridgelabz.bookstore.repository.IOrderRepository;
import com.bridgelabz.bookstore.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService{
    @Autowired
    IUserRepository iUserRepository;
    @Autowired
    IBookRepository iBookRepository;
    @Autowired
    IOrderRepository iOrderRepository;
    @Autowired
    ICartRepository iCartRepository;
    @Override
    public ResponseEntity<ResponseDTO> placeOrder(OrderDTO orderDTO){
        Optional<User> user = iUserRepository.findById(orderDTO.getUserId());
        Optional<Book> book = iBookRepository.findById(orderDTO.getBookId());
        int quantity = (int) book.get().getQuantity();
        if (user.isPresent()){
            if (book.isPresent()) {
                if (quantity >= orderDTO.getQuantity()) {
                    Order order = new Order(user.get(), book.get(),orderDTO.getQuantity(), orderDTO.getAddress(), false);
                    order.setPrice((int) book.get().getPrice()*orderDTO.getQuantity());
                    iOrderRepository.save(order);
                    book.get().setQuantity(book.get().getQuantity() - orderDTO.getQuantity());
                    book.get().setBookId(book.get().getBookId());
                    iBookRepository.save(book.get());
                    ResponseDTO responseDTO = new ResponseDTO("Successfully order is Place", order);
                    return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.CREATED);
                }
                throw new Exception("Book is Not Available check the Quantity");
            }
            throw new Exception("Book is Not Present");
        }
        throw new Exception("User is Not Present");
    }

    @Override
    public ResponseEntity<ResponseDTO> placeOrderByCartId(int cartId,String address){
       Cart cart = iCartRepository.findById(cartId).get();
       if (cart.getCartId() == cartId) {
           User user1 = cart.getUserId();
           Optional<User> user = iUserRepository.findById(user1.getUserId());
           Book book1 = cart.getBookID();
           Optional<Book> book = iBookRepository.findById(book1.getBookId());
           int quantity = (int) book.get().getQuantity();
           if (quantity >= cart.getQuantity()) {
               Order order = new Order(user.get(),book.get(),cart.getQuantity(),address,false);
               order.setPrice((int) (book.get().getPrice()*cart.getQuantity()));
               iOrderRepository.save(order);
               book.get().setQuantity(book.get().getQuantity() - cart.getQuantity());
               book.get().setBookId(book.get().getBookId());
               iBookRepository.save(book.get());
               iCartRepository.delete(cart);
               ResponseDTO responseDTO = new ResponseDTO("Successfully order is Place", order);
               return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.CREATED);
           }else {
               throw new Exception("Book is Not Available check the Quantity");
           }
       }else {
           throw new Exception("Cart is Not Found");
       }
    }

    @Override
    public ResponseEntity<ResponseDTO> getAllOrder() {
        List<Order> orderList = iOrderRepository.findAll();
        ResponseDTO responseDTO = new ResponseDTO("Getting All Order", orderList);
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }
    @Override
    public ResponseEntity<ResponseDTO> getOrderById(int id){
        Order order = iOrderRepository.findById(id).get();
        ResponseDTO responseDTO = new ResponseDTO("Getting Order", order);
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDTO> deleteById(int id){
        Order order = iOrderRepository.findById(id).get();
        if (order.getOrderId() == id) {
            iOrderRepository.deleteByID(id);
            ResponseDTO responseDTO = new ResponseDTO("Delete order Information", order);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }else {
            throw new Exception("Order is Not Present");
        }
    }

    @Override
    public ResponseEntity<ResponseDTO> cancelOrder(int id,boolean ans){
        Optional<Order> order = iOrderRepository.findById(id);
        if (order.isPresent()) {
            if (ans) {
                order.get().setCancel(true);
                order.get().setOrderId(id);
                iOrderRepository.save(order.get());
                ResponseDTO responseDTO = new ResponseDTO("Order is cancel",order);
                return new ResponseEntity<>(responseDTO,HttpStatus.OK);
            }else {
                order.get().setCancel(false);
                order.get().setOrderId(id);
                iOrderRepository.save(order.get());
                ResponseDTO responseDTO = new ResponseDTO("Order is Present",order);
                return new ResponseEntity<>(responseDTO,HttpStatus.OK);
            }
        }
        throw new Exception("Order is Not Found");
    }

    public ResponseEntity<ResponseDTO> updateById(int id,OrderDTO orderDTO) {
        Optional<Order> order1 = iOrderRepository.findById(id);
        if (order1.isPresent()) {
            int previousQuantity = order1.get().getQuantity();
            Book previousBook = order1.get().getBookId();
            previousBook.setQuantity(previousBook.getQuantity()+previousQuantity);
            previousBook.setBookId(previousBook.getBookId());
            iBookRepository.save(previousBook);
            Optional<User> user = iUserRepository.findById(orderDTO.getUserId());
            if (user.isPresent()) {
                Optional<Book> book = iBookRepository.findById(orderDTO.getBookId());
                if (book.isPresent()) {
                    int quantity = (int) book.get().getQuantity();
                    if (quantity >= orderDTO.getQuantity()) {
                        Order order = new Order(user.get(), book.get(), orderDTO.getQuantity(), orderDTO.getAddress(), false);
                        order.setPrice((int) book.get().getPrice()*orderDTO.getQuantity());
                        order.setOrderId(id);
                        iOrderRepository.save(order);
                        book.get().setQuantity(book.get().getQuantity() - orderDTO.getQuantity());
                        book.get().setBookId(book.get().getBookId());
                        iBookRepository.save(book.get());
                        ResponseDTO responseDTO = new ResponseDTO("Successfully order is Place", order);
                        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.CREATED);
                    }
                    throw new Exception("Book is Not Available check the Quantity");
                }
                throw new Exception("Book is Not Present");
            }
            throw new Exception("User is Not Present");
        }
        throw new Exception("Order is Not Found");
    }
}
