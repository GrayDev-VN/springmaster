package com.springmaster.study.no_spring;

public class ToPho {
    private BanhPho banhPho;
    private ThitBo thitBo;

    public ToPho() {
        // vấn đề: Tightly Couple là bị trói chặt vào nhau. Cực kì khó bảo trì code
        // giải quyết vấn đề này Spring sẽ sử dụng IoC
        // giao toàn quyền cho một ông chủ nhà hàng hoặc chủ nhà => Spring Container (Quản lý Bean)
        // thì các bạn muốn nó giao nguyên liệu để làm tô phở thì nó sẽ dùng DI (thêm phụ thuộc)
        this.banhPho = new BanhPho();
        this.thitBo = new ThitBo();
    }

    public void phucVu() {
        System.out.println("Phục vụ tô phở gồm:" + banhPho.layBanhPho() + "và" + thitBo.layThitBo());
    }

}
