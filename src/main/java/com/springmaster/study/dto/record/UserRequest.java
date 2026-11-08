package com.springmaster.study.dto.record;

import com.springmaster.study.annotation.Cccd;
import jakarta.validation.constraints.*;

/*
Tại sao ở request đã validate rồi nhưng ở entity vấn có validate?
  - Request: kiểm tra dữ liệu của client gửi lên
  - Entity: mô tả cột lưu trong database
 Vậy tại sao lại cần dùng cả hai?
 -> Vì dữ liệu có thể đươc ghi từ nhiều nguồn: Client -> Request validation -> Service -> database
Validation ở request chỉ bảo vệ luồng đi qua request đó, ràng buộc trong database bảo vệ dữ liệu được lưu từ mọi nguồn.

- Request: kiểm tra sớm để trả lỗi cho client, cn dùng @Valid ở controller kích hoạt validation request
- entity khai báo độ dài cột mong muốn cho JPA, không thay thế validation ở request. Cần đảm bảo cột thực tế trong database có giới hạn tương ứng


 */
public record UserRequest(
        /* Nhớ 3 annotation này:
              - @NotNull: phải có giá trị
              - @NotEmpty: phải có giá trị và không rỗng, dùng được cho chuỗi, collection, map, array.
              - @NotBlank: chuỗi phải có ít nhất một ký tự không phải khoảng trắng.
        */
        // @NotBlank đã loại cả null, chuỗi rỗng và chuỗi chỉ có khoảng trắng.
        @NotBlank(message = "Tên không được để trống")
        String name,

        @NotBlank(message = "Địa chỉ không được bỏ trống")
        @Size(max = 30, message = "Địa chỉ tối đa 30 ký tự")
        String address,
        // Điện thoại là chuỗi để giữ số 0 đầu; kiểm tra chữ số bằng @Pattern.
        @NotBlank(message = "Số điện thoại không được để trống")
        @Pattern(regexp = "[0-9]{9,10}", message = "Số điện thoại phải gồm 9 đến 10 chữ số")
        String phone,

        @Email(message = "Email không đúng định dạng")
        @NotBlank(message = "Email không được để trống")
        String email,
        String username,
        String password,

        @Cccd
        String cccd
){
}
