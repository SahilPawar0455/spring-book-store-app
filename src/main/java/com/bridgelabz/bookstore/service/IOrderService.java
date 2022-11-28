package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.OrderDTO;
import com.bridgelabz.bookstore.dto.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface IOrderService {
    ResponseEntity<ResponseDTO> placeOrder(@RequestBody OrderDTO orderDTO);
    ResponseEntity<ResponseDTO> getAllOrder();
    ResponseEntity<ResponseDTO> getOrderById(int id);
    ResponseEntity<ResponseDTO> deleteById(int id);
    ResponseEntity<ResponseDTO> cancelOrder(int id,boolean ans);
    ResponseEntity<ResponseDTO> updateById(int id,OrderDTO orderDTO);
    ResponseEntity<ResponseDTO> placeOrderByCartId(int cartId,String address);
}
