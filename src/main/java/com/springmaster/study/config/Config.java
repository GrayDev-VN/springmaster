package com.springmaster.study.config;

// Demo ModelMapper: toàn bộ cấu hình được comment vì hiện tại dùng MapStruct.
// Muốn thử lại: bật dependency trong build.gradle, cấu hình dưới đây và field trong service.
// UserRp cần constructor không tham số; nếu vẫn giữ @Builder thì thêm cả @AllArgsConstructor.
//
// import org.modelmapper.ModelMapper;
// import org.modelmapper.convention.MatchingStrategies;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
//
// @Configuration // Spring đọc các khai báo bean trong class này.
// public class Config {
//
//     @Bean // Spring quản lý object trả về để service có thể inject.
//     public ModelMapper modelMapper() {
//         ModelMapper modelMapper = new ModelMapper();
//         modelMapper.getConfiguration()
//                 // Cho phép xét field ngoài getter/setter; mặc định chỉ xét field public.
//                 // Muốn xét field private cần thêm setFieldAccessLevel(AccessLevel.PRIVATE).
//                 // DTO hiện tại có getter/setter nên không bắt buộc bật field matching.
//                 .setFieldMatchingEnabled(true)
//                 // Ghép tên thuộc tính chặt chẽ; không yêu cầu hai class có mọi field giống nhau.
//                 // STRICT không tự báo lỗi mọi field chưa map; có thể gọi validate() để kiểm tra.
//                 .setMatchingStrategy(MatchingStrategies.STRICT);
//
//         // name -> name được map tự động. Chỉ khai báo riêng khi cần đổi tên/quy tắc.
//         // modelMapper.typeMap(User.class, UserRp.class).addMappings(mapper -> {
//         //     mapper.map(User::getName, UserRp::setName);
//         // });
//         // Ví dụ trên cần import User và UserRp nếu bật lại.
//         return modelMapper;
//     }
// }
