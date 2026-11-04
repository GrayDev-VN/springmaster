package com.springmaster.study.services.impl;

import com.springmaster.study.dto.request.UserRq;
import com.springmaster.study.dto.response.UserRp;
import com.springmaster.study.entity.User;
import com.springmaster.study.repository.UserRepository;
import com.springmaster.study.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
// Sinh constructor có tham số cho các field final chưa được khởi tạo
// và các field @NonNull chưa được khởi tạo.
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void create(UserRq userRq) {
        userRepository.save(mapToEntity(userRq));
    }

    @Override
    public void update(String id, UserRq userRq) {
        Optional<User> user = userRepository.findById(id);

        if(user.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }

        User userUpdate = user.get();
        userUpdate.setAddress(userRq.getAddress());
        userUpdate.setPhone(userRq.getPhone());
        userUpdate.setName(userRq.getName());
        userUpdate.setEmail(userRq.getEmail());
        userUpdate.setPassword(userRq.getPassword());
        userUpdate.setUsername(userRq.getUsername());
        userRepository.save(userUpdate);

    }

    @Override
    public List<UserRp> index() {
        List<User> list = userRepository.findAll();
        return list.stream().map(this::mapToDto).toList();
    }

    @Override
    public void delete(String id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }
        userRepository.delete(user.get());
    }

    private User mapToEntity(UserRq userRq) {
        return User.builder()
                .name(userRq.getName())
                .password(userRq.getPassword())
                .email(userRq.getEmail())
                .phone(userRq.getPhone())
                .address(userRq.getAddress())
                .username(userRq.getUsername())
                .build();
    }

    private UserRp mapToDto(User user){
        return UserRp.builder()
                .id(user.getId())
                .name(user.getName())
                .username(user.getUsername())
                .email(user.getEmail())
                .phone(user.getPhone())
                .address(user.getAddress())
                .build();
    }
}
