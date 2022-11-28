package com.bridgelabz.bookstore.repository;

import com.bridgelabz.bookstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User,Integer> {
    @Query(value = "SELECT * FROM user where email = :email", nativeQuery = true)
    User findByEmail(String email);
}
