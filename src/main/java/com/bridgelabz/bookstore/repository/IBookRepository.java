package com.bridgelabz.bookstore.repository;

import com.bridgelabz.bookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IBookRepository extends JpaRepository<Book,Integer> {
    @Query(value = "select * from book where book_name = :bookName",nativeQuery = true)
    Book findBookByBookName(String bookName);

    @Query(value = "select * from book ORDER BY price DESC ",nativeQuery = true)
    List<Book> sortingBookPriceInDescendingOrder();

    @Query(value = "select * from book ORDER BY price ASC ",nativeQuery = true)
    List<Book> sortingBookPriceInAscendingOrder();
}
