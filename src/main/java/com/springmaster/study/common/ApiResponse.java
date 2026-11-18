package com.springmaster.study.common;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
/*
 code: mã do ứng dụng quy ước
 message: thông báo chung
 result: dữ liệu cụ thể của api
 */
public record ApiResponse<T>(
        int code,
        String message,
        T result
) {
    public static <T> ApiResponse<T> success(T result){
        return new ApiResponse<T>(200, "success", result);
    }

    public static <T> ApiResponse<T> error(int code, String message){
        return new ApiResponse<T>(code, message, null);
    }

    public static <T> ApiResponse<T> errorListDataMessage(int code, String message, T result){
        return new ApiResponse<T>(code, message, result);
    }
}
