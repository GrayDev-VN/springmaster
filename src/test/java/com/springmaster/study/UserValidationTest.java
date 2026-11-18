package com.springmaster.study;

import com.springmaster.study.controller.UserController;
import com.springmaster.study.exception.GlobalExceptionHandler;
import com.springmaster.study.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserValidationTest {
    @Test
    void invalidFieldsReturn400BeforeCallingServiceForCreateAndUpdate() throws Exception {
        UserService service = mock(UserService.class);
        // Chạy MVC và Bean Validation thật, không khởi động JPA/database.
        try (LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean()) {
            validator.afterPropertiesSet();
            MockMvc mvc = MockMvcBuilders.standaloneSetup(new UserController(service))
                    .setControllerAdvice(new GlobalExceptionHandler())
                    .setValidator(validator)
                    .build();

            String body = """
                    {
                      "name": "   ",
                      "address": "1234567890123456789012345678901",
                      "phone": "01234567890",
                      "email": "invalid-email"
                    }
                    """;

            for (var request : new org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder[]{
                    post("/api/v1/users"), put("/api/v1/users/test-id")}) {
                mvc.perform(request.contentType(MediaType.APPLICATION_JSON).content(body))
                        .andExpect(status().isBadRequest())
                        .andExpect(jsonPath("$.code").value(9999))
                        .andExpect(jsonPath("$.result.name").value("Tên không được để trống"))
                        .andExpect(jsonPath("$.result.address").value("Địa chỉ tối đa 30 ký tự"))
                        .andExpect(jsonPath("$.result.phone").value("Số điện thoại phải gồm 9 đến 10 chữ số"))
                        .andExpect(jsonPath("$.result.email").value("Email không đúng định dạng"));
            }
            verifyNoInteractions(service);
        }
    }
}
