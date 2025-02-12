package com.example.usermanagement.exceptions;

public class RoomNotAvailableException extends RuntimeException{
    public RoomNotAvailableException(String message) {
        super(message);
    }
}
