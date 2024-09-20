package com.baeksoo.shop.exception;

public class DuplicateMemberException extends RuntimeException{
    public DuplicateMemberException(String message) {
        super(message);
    }
}
