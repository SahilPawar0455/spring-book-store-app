package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.dto.OrderDTO;
import com.bridgelabz.bookstore.dto.ResponseDTO;
import com.bridgelabz.bookstore.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {
    @Autowired
    IOrderService iOrderService;

    @PostMapping("/placeOrder")
    public ResponseEntity<ResponseDTO> placeOrder(@RequestBody OrderDTO orderDTO){
        return iOrderService.placeOrder(orderDTO);
    }

    @PostMapping("/placeOrderByCartId/{cartId}")
    public ResponseEntity<ResponseDTO> placeOrderByCartId(@PathVariable int cartId,
                                                          @RequestParam(value = "address") String address){
        return iOrderService.placeOrderByCartId(cartId,address);
    }

    @GetMapping("/getAllOrder")
    public ResponseEntity<ResponseDTO> getAllOrder(){
        return iOrderService.getAllOrder();
    }

    @GetMapping("/getOrderById/{id}")
    public ResponseEntity<ResponseDTO> getOrderById(@PathVariable int id){
        return iOrderService.getOrderById(id);
    }

    @DeleteMapping("/deleteOrderById/{id}")
    public ResponseEntity<ResponseDTO> deleteById(@PathVariable int id){
        return iOrderService.deleteById(id);
    }

    @PutMapping("/changeOrderStatus/{id}")
    public ResponseEntity<ResponseDTO> cancelOrder(@PathVariable int id,
                                                   @RequestParam(value = "ans") boolean ans){
        return iOrderService.cancelOrder(id,ans);
    }

    @PutMapping("/updateOrder/{id}")
    ResponseEntity<ResponseDTO> updateById(@PathVariable int id,
                                           @RequestBody OrderDTO orderDTO){
        return iOrderService.updateById(id,orderDTO);
    }
}
