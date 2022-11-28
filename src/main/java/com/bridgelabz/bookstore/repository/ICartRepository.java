package com.bridgelabz.bookstore.repository;

import com.bridgelabz.bookstore.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ICartRepository extends JpaRepository<Cart,Integer> {
    @Query(value = "DELETE from cart where cart_id = :id",nativeQuery = true)
    void abc(int id);
}
