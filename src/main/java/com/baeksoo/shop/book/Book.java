package com.baeksoo.shop.book;


import com.baeksoo.shop.category.Category;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;



@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String image;
    private Integer price;
    private String title;
    private String author;
    private String isbn;
    private LocalDateTime publishedDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

}

enum Status {
    AVAILABLE,
    LOANED
}