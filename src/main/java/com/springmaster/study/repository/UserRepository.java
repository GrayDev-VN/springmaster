package com.springmaster.study.repository;

import com.springmaster.study.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
/* Tạo 1 interface kế thừa JpaRepository, trong đó:
- User: kiểu Entity mà repository quản lý
- String là kiểu của field @Id trong User
Nhờ kế thừa, UserRepository đã có các phương thức như:
- save(user): lưu mới hoặc cập nhật
- findById(id): tìm kiếm theo id
- findAll: lấy tất cả
- existsById(id): kiểm tra tồn tại
- deleteById(id): Xóa theo Id
-> Không cần phải tự khai báo lại nhưng phương thức CRUD này
*/
public interface UserRepository extends JpaRepository<User, String> {
}
