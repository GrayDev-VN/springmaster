package com.springmaster.study.exception;

// Lớp cha cho lỗi nghiệp vụ tự định nghĩa; không tự tạo HTTP response.
public abstract class BaseException extends RuntimeException {
    protected BaseException(String message) {
        super(message);
    }
}
