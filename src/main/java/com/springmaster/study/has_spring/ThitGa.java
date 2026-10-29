package com.springmaster.study.has_spring;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary // ưu tiên lấy class này
public class ThitGa implements Thit{
    @Override
    public String layThit() {
        return "50g thịt gà";
    }
}
