package com.springmaster.study.bean;

import org.springframework.stereotype.Component;

@Component //mặc định là singleton -- không khai báo là prototype
public class Chef {
    public Chef() {
        System.out.println("Have a chef at work");
    }
}