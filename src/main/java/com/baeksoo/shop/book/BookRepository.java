package com.baeksoo.shop.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(value = "SELECT * FROM book ORDER BY title LIMIT 100", nativeQuery = true)
    List<Book> findLimit100Book();

    @Query(value = "SELECT * FROM book ORDER BY title LIMIT 30", nativeQuery = true)
    List<Book> findLimit30Book();
}
