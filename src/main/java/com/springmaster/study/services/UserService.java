package com.springmaster.study.services;

import com.springmaster.study.dto.request.UserRq;
import com.springmaster.study.dto.response.UserRp;

import java.util.List;

public interface UserService {
    void create(UserRq userRq);
    void update(String id, UserRq userRq);
    List<UserRp> index();
    void delete(String id);
}
