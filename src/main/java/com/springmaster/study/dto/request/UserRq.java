package com.springmaster.study.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class UserRq {
    private String name;
    private String address;
    private String phone;
    private String email;
    private String username;
    private String password;

}
