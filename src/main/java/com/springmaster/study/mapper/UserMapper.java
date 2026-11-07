package com.springmaster.study.mapper;

import com.springmaster.study.dto.record.UserRequest;
import com.springmaster.study.dto.record.UserResponse;
import com.springmaster.study.dto.request.UserRq;
import com.springmaster.study.dto.response.UserRp;
import com.springmaster.study.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

//MapStruct sinh class UserMapperImpl khi compile
//chọn SPRING để class được sinh có @Component
// nhờ đó có thể inject UserMapper vào service
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserMapper {

    //Chuyển đổi entity sang dto trả về cho client
    //MapStruct ự ánh xạ các thuộc tính cùng tên và kiểu tương thích
    //UserResponse không có password nên không đưa password vào response
    UserResponse toResponse(User user);


    @Mapping(target ="id", ignore = true)
    User toEntity(UserRequest userRequest);

    // API hiện tại dùng DTO class; hai method phía trên giữ để học mapping record.
    // Thuộc tính cùng tên được ánh xạ tự động, không cần name -> name.
    @Mapping(target = "id", ignore = true)
    User toEntity(UserRq userRq);

    UserRp toDto(User user);
}
