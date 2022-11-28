package com.bridgelabz.bookstore.repository;

import com.bridgelabz.bookstore.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepository extends JpaRepository<Order,Integer> {

    @Query(value = "DELETE from ORDER_DETAIL where order_id = :order_id",nativeQuery = true)
    void deleteByID(int order_id);
}
