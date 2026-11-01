package com.springmaster.study;

import com.springmaster.study.bean.Chef;
import com.springmaster.study.bean.Order;
import com.springmaster.study.has_spring.ToPho;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringbootApplication implements CommandLineRunner {
    private final ToPho toPho;

    @Autowired
    private ApplicationContext applicationContext;

    public SpringbootApplication(ToPho toPho) {
        this.toPho = toPho;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        toPho.phucVu();
        //Spring Container (ApplicationContext): chính là cái kho lưu trữ và điều hành của nhà hàng
        //khi nhà hàng mở cử (app start)
        //ông chủ spring sẽ quét một lượt danh sách và tự tay chuẩn bị các đồ dùng cần thiết rồi cất vào kho

        //Bean: chính là những dụng cụ/nguyên liệu đã được ông chủ chuẩn bị sẵn và dán nhãn
        //Một khi đã nằm trong kho này, Spring sẽ chịu trách nhiệm nuôi sống và vứt bỏ chúng khi cần.
        System.out.println("check singleton");
        Chef chef1 = applicationContext.getBean(Chef.class);
        Chef chef2 = applicationContext.getBean(Chef.class);
        System.out.println("chef1 == chef2" + (chef1==chef2));

        System.out.println("Check prototype");
        Order order1 = applicationContext.getBean(Order.class);
        Order order2 = applicationContext.getBean(Order.class);
        System.out.println("order1 == order2" + (order1==order2));
    }
}
