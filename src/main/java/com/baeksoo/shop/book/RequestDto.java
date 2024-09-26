package com.baeksoo.shop.book;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RequestDto {
    private Long id;
    private String title;
    private String author;
    private Integer price;
    private String image;
    private String isbn;
    private String status;
    private String category;
}
