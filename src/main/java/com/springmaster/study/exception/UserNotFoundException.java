package com.springmaster.study.exception;

public class UserNotFoundException extends BaseException {
    public UserNotFoundException() {
        super("User not found");
    }
}
