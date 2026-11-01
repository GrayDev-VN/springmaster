package com.springmaster.study.configuration;

import com.springmaster.study.service.ConnectionService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader {
    @Autowired
    private ConnectionService connectionService;

    public DatabaseLoader(){
        System.out.println("Create a new DatabaseLoader!");
        try {
            connectionService.connect();
        } catch (NullPointerException e) {
            System.out.println(e);
        }
    }
//    @PostConstruct
//    public void init() {
//        System.out.println("Create a new DatabaseLoader!");
//        try {
//            connectionService.connect();
//        } catch (NullPointerException e) {
//            System.out.println(e);
//        }
//    }
}
