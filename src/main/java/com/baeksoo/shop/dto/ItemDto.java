package com.baeksoo.shop.dto;

import lombok.Builder;
import lombok.Getter;
import org.antlr.v4.runtime.misc.NotNull;


public class ItemDto {

    @Getter
    @Builder
    public static class Request {
        private String title;
        private Integer price;
    }

    @Getter
    @Builder
    public static class Response {
        private Long id;
        private String title;
        private Integer price;
    }


}
