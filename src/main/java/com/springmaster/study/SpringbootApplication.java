package com.springmaster.study;

import com.springmaster.study.has_spring.ToPho;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootApplication implements CommandLineRunner {
    private final ToPho toPho;

    public SpringbootApplication(ToPho toPho) {
        this.toPho = toPho;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        toPho.phucVu();
    }
}
