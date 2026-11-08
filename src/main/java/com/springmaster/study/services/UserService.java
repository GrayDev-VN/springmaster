package com.springmaster.study.services;

import com.springmaster.study.dto.record.UserRequest;
import com.springmaster.study.dto.record.UserResponse;

import java.util.List;

public interface UserService {
    void create(UserRequest userRequest);
    void update(String id, UserRequest userRequest);
    List<UserResponse> index();
    void delete(String id);
}
