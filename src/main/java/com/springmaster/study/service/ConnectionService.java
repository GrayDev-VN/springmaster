package com.springmaster.study.service;

import org.springframework.stereotype.Service;

@Service
public class ConnectionService {
    private boolean isReady=false;

    public ConnectionService(){
        System.out.println("Wait connect ....");
        this.isReady=true;
    }

    public void connect(){
        if(isReady){
            System.out.println("success");
        } else {
            System.out.println("fail");
        }
    }

    public String getStatus() {
        return "online";
    }
}
