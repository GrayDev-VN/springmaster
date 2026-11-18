package com.springmaster.study.controller;

import com.springmaster.study.common.ApiResponse;
import com.springmaster.study.common.BaseController;
import com.springmaster.study.dto.record.UserRequest;
import com.springmaster.study.dto.record.UserResponse;
import com.springmaster.study.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController extends BaseController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserResponse>>> showListUsers() {
        return ResponseEntity.ok(createSuccessResponse(userService.index()));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<String>> createNewUser(@Valid @RequestBody UserRequest userRequest) {
        userService.create(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createSuccessResponse("Create a new user successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> updateUser(@PathVariable("id") String id, @Valid @RequestBody UserRequest userRequest) {
        userService.update(id, userRequest);
        return ResponseEntity.ok(createSuccessResponse("Update a user successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable("id") String id) {
        userService.delete(id);
        return ResponseEntity.ok(createSuccessResponse("Delete a user successfully"));
    }


}
