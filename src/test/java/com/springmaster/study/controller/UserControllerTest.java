package com.springmaster.study.controller;

import com.springmaster.study.dto.record.UserRequest;
import com.springmaster.study.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@Slf4j
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    // xác định input-output của testcase
    private UserRequest userRequest;
    private ResponseEntity<String> responseEntity;

    // Khởi tạo dữ liệu ban đầu

    @Test
    void createUser() {
        // GIVEN

        // WHEN, THEN

      
    }
}
