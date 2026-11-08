package com.springmaster.study.controller;

import com.springmaster.study.dto.record.UserRequest;
import com.springmaster.study.dto.record.UserResponse;
import com.springmaster.study.dto.response.UserRp;
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
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> showListUsers() {
        return new ResponseEntity<>(userService.index(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createNewUser(@Valid @RequestBody UserRequest userRequest) {
        userService.create(userRequest);
        return new ResponseEntity<>("Create a new user successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable("id") String id, @Valid @RequestBody UserRequest userRequest) {
        userService.update(id, userRequest);
        return new ResponseEntity<>("Update a user successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") String id) {
        userService.delete(id);
        return new ResponseEntity<>("Delete a user successfully", HttpStatus.OK);
    }


}
