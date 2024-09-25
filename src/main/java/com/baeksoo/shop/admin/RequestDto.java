package com.baeksoo.shop.admin;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RequestDto {
    private String title;
    private String author;
    private Integer price;
    private Long categoryId;
}
