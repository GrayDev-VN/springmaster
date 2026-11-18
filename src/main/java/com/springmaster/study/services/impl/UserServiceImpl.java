package com.springmaster.study.services.impl;

import com.springmaster.study.dto.record.UserRequest;
import com.springmaster.study.dto.record.UserResponse;
import com.springmaster.study.dto.response.UserRp;
import com.springmaster.study.entity.User;
import com.springmaster.study.exception.UserNotFoundException;
import com.springmaster.study.mapper.UserMapper;
import com.springmaster.study.repository.UserRepository;
import com.springmaster.study.services.UserService;
import lombok.RequiredArgsConstructor;
// import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
// import tools.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
// Sinh constructor có tham số cho các field final chưa được khởi tạo
// và các field @NonNull chưa được khởi tạo.
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    // Demo: chỉ inject khi bật lại ModelMapper bean/dependency hoặc dùng Jackson bean.
    // private final ObjectMapper objectMapper;
//    private final ModelMapper modelMapper;

    // Sử dụng MapStruct
    private final UserMapper userMapper;
    @Override
    public void create(UserRequest userRequest) {
        userRepository.save(mapToEntity(userRequest));
    }

    @Override
    public void update(String id, UserRequest userRequest) {
        Optional<User> user = userRepository.findById(id);

        if(user.isEmpty()) {
            throw new UserNotFoundException();
        }

        User userUpdate = user.get();
        // Record cung cấp accessor name(), email()... thay vì getter getName().
        userUpdate.setAddress(userRequest.address());
        userUpdate.setPhone(userRequest.phone());
        userUpdate.setName(userRequest.name());
        userUpdate.setEmail(userRequest.email());
        userUpdate.setPassword(userRequest.password());
        userUpdate.setUsername(userRequest.username());
        userUpdate.setCccd(userRequest.cccd());
        userRepository.save(userUpdate);

    }

    @Override
    public List<UserResponse> index() {
        List<User> list = userRepository.findAll();
        return list.stream().map(this::mapToDto).toList();
    }

    @Override
    public void delete(String id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            throw new UserNotFoundException();
        }
        userRepository.delete(user.get());
    }

    private User mapToEntity(UserRequest userRequest) {
        // MapStruct đang được sử dụng thực tế trong create().
        // Mapper copy password nguyên bản; mã hóa mật khẩu là trách nhiệm của service.
        return userMapper.toEntity(userRequest);
    }

    private UserResponse mapToDto(User user) {
        // Demo 1 - ObjectMapper (Jackson): chủ yếu phục vụ JSON/object conversion.
        // Dùng bean đã cấu hình thay vì new ObjectMapper() mỗi lần gọi.
        // User có password, UserRp không có: bỏ qua field dư cho riêng phép chuyển này.
        // UserRp cần constructor không tham số (hoặc cấu hình Jackson creator/builder).
        // return objectMapper.rebuild()
        //         .disable(tools.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        //         .build().convertValue(user, UserRp.class);

        // Demo 2 - ModelMapper: suy luận mapping lúc runtime; lỗi có thể xuất hiện khi chạy.
        // Không chỉ dành cho project cá nhân; hiệu năng cần đo trên dữ liệu thực tế.
        // UserRp cần constructor không tham số để ModelMapper tạo đối tượng đích.
        // return modelMapper.map(user, UserRp.class);

        // Demo 3 - MapStruct: sinh code Java khi compile, không dùng reflection để mapping.
        // Đây là cách đang dùng trong index(); lỗi target chưa map sẽ bị báo khi build.
        return userMapper.toResponse(user);
    }
}
