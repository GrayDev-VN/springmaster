package com.springmaster.study.has_spring;

import org.springframework.stereotype.Component;

@Component
public class ThitBo implements Thit {
//    public String layThitBo() {
//        return "100gram thịt bò";
//    }
    @Override
    public String layThit() {
        return "100g Banh pho tuoi";
    }
}

