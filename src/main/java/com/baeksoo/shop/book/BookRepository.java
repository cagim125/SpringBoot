package com.baeksoo.shop.book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
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

    @Query(value = "SELECT * FROM book b ORDER BY b.id DESC", nativeQuery = true)
    Page<Book> findByIdOrderByDesc(Pageable pageable);

    Slice<Book> findByTitleContains(String keyword, Pageable pageable);
}
