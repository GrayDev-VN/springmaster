package com.springmaster.study;

import com.springmaster.study.controller.UserController;
import com.springmaster.study.dto.record.UserRequest;
import com.springmaster.study.dto.record.UserResponse;
import com.springmaster.study.exception.GlobalExceptionHandler;
import com.springmaster.study.exception.UserNotFoundException;
import com.springmaster.study.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class UserResponseTest {
    private UserService service;
    private MockMvc mvc;
    private static final String BODY = """
            {"name":"An","address":"Ha Noi","phone":"0912345678",
             "email":"an@example.com","username":"an","password":"test-password",
             "cccd":"123456789012"}
            """;

    @BeforeEach
    void setUp() {
        service = mock(UserService.class);
        mvc = MockMvcBuilders.standaloneSetup(new UserController(service))
                .setControllerAdvice(new GlobalExceptionHandler()).build();
    }

    @Test
    void successfulCrudUsesEnvelopeAndCorrectHttpStatus() throws Exception {
        when(service.index()).thenReturn(List.of(
                new UserResponse("id", "An", "Ha Noi", "0912345678",
                        "an@example.com", "an", "123456789012")));
        mvc.perform(get("/api/v1/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.result[0].name").value("An"))
                .andExpect(jsonPath("$.result[0].password").doesNotExist());
        mvc.perform(post("/api/v1/users").contentType(MediaType.APPLICATION_JSON).content(BODY))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("success"))
                .andExpect(jsonPath("$.result").value("Create a new user successfully"));
        mvc.perform(put("/api/v1/users/id").contentType(MediaType.APPLICATION_JSON).content(BODY))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("Update a user successfully"));
        mvc.perform(delete("/api/v1/users/id"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("Delete a user successfully"));
        verify(service).create(any(UserRequest.class));
        verify(service).update(eq("id"), any(UserRequest.class));
        verify(service).delete("id");
    }

    @Test
    void missingUserReturns404ForUpdateAndDelete() throws Exception {
        doThrow(new UserNotFoundException()).when(service).update(eq("missing"), any(UserRequest.class));
        doThrow(new UserNotFoundException()).when(service).delete("missing");
        mvc.perform(put("/api/v1/users/missing").contentType(MediaType.APPLICATION_JSON).content(BODY))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value(404))
                .andExpect(jsonPath("$.message").value("User not found"));
        mvc.perform(delete("/api/v1/users/missing"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("User not found"));
    }

    @Test
    void malformedOrMissingJsonReturns400WithoutCallingService() throws Exception {
        mvc.perform(post("/api/v1/users").contentType(MediaType.APPLICATION_JSON).content("{"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(400));
        mvc.perform(post("/api/v1/users").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(400));
        verifyNoInteractions(service);
    }

    @Test
    void unexpectedErrorReturns500WithoutExposingInternalMessage() throws Exception {
        when(service.index()).thenThrow(new IllegalStateException("internal database detail"));
        mvc.perform(get("/api/v1/users"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.code").value(500))
                .andExpect(jsonPath("$.message").value("Lỗi hệ thống, vui lòng thử lại sau"))
                .andExpect(jsonPath("$.result").doesNotExist());
    }
}
