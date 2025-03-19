package com.example.demo.exceptions;

public class DataNotFoundException extends RuntimeException {
    public DataNotFoundException() {
        super("Average percentage not found");
    }
    public DataNotFoundException(String msg) {
        super(msg);
    }
}
