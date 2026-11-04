package com.springmaster.study.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="tl_user")
// Thay vì phải viết getter, setter thì sử dụng thư viện getter, setter của lombok để code ngắn hơn
@Getter
@Setter
// Tạo ra constructor có tham số và không tham số
@NoArgsConstructor
@AllArgsConstructor
@Builder // giúp cho các bạn có thể insert vào user dễ hơn thay vì phải constructor (thêm nhưng không cần thứ tự á)
public class User {
    // Các field trong java thì code theo phong cách camelCase,ví dụ fullName
    // Các tên cột trong database theo phong cách snake_case, ví dụ full_name
    // Mặc định khi không khai báo column thì nó vẫn là snake_case
    // Lên mạng search các annotaion tương ứng nhé
    // Ở bài này thì chúng ta không cần khai báo quá nhiều các kiểu dữ liệu làm gì vì sau này học Flyway thì chúng ta sẽ config chủ yếu ở đó nhé
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;

    @Column(name="address", length = 30)
    private String address;

    @Column(name="phone", length = 10)
    private String phone;

    @Column(unique = true)
    private String email;
    private String username;
    private String password;
}
