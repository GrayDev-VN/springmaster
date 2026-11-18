package com.springmaster.study;

import com.springmaster.study.dto.record.UserRequest;
import com.springmaster.study.entity.User;
import com.springmaster.study.exception.UserNotFoundException;
import com.springmaster.study.mapper.UserMapper;
import com.springmaster.study.repository.UserRepository;
import com.springmaster.study.services.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {
    @Test
    void updateCopiesCccdAndPreservesExistingId() {
        UserRepository repository = mock(UserRepository.class);
        UserServiceImpl service = new UserServiceImpl(repository, mock(UserMapper.class));
        User existing = User.builder().id("id").cccd("111111111111").build();
        when(repository.findById("id")).thenReturn(Optional.of(existing));
        UserRequest request = new UserRequest("An", "Ha Noi", "0912345678",
                "an@example.com", "an", "test-password", "222222222222");

        service.update("id", request);

        assertEquals("222222222222", existing.getCccd());
        assertEquals("id", existing.getId());
        verify(repository).save(existing);
    }

    @Test
    void missingUserStopsBeforeSaveOrDelete() {
        UserRepository repository = mock(UserRepository.class);
        UserServiceImpl service = new UserServiceImpl(repository, mock(UserMapper.class));
        when(repository.findById("missing")).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> service.update("missing", null));
        assertThrows(UserNotFoundException.class, () -> service.delete("missing"));
        verify(repository, never()).save(any(User.class));
        verify(repository, never()).delete(any(User.class));
    }
}
