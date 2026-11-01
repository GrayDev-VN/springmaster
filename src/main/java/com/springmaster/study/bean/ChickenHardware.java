package com.springmaster.study.bean;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class ChickenHardware {
    public ChickenHardware(){
        System.out.println("Create a new ChickenHardware!");
    }

    @PostConstruct
    public void init(){
        System.out.println("Init a new ChickenHardware!");
    }

    @PreDestroy
    public void cleanup(){
        System.out.println("Cleanup a new ChickenHardware!");
    }
}
