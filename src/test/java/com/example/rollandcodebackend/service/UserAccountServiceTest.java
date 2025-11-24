package com.example.rollandcodebackend.service;


import com.example.rollandcodebackend.dto.LoginRequest;
import com.example.rollandcodebackend.dto.LoginResponse;
import com.example.rollandcodebackend.dto.RegisterUserRequest;
import com.example.rollandcodebackend.entity.UserAccount;
import com.example.rollandcodebackend.repository.UserAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserAccountServiceTest {

    @Mock
    private UserAccountRepository repository;

    @InjectMocks
    private UserAccountService service;

    private RegisterUserRequest registerRequest;

    @BeforeEach
    void setUp() {
        registerRequest = new RegisterUserRequest() {
            public String getName() {
                return "Cristobal";
            }
            public String getEmail() {
                return "cristobal@example.com";
            }
            public String getPassword() {
                return "123456";
            }
        };
    }

    @Test
    void givenNewEmail_whenRegister_thenSavesUserAndReturnsResponse() {
        when(repository.findByEmail("cristobal@example.com")).thenReturn(Optional.empty());
        ArgumentCaptor<UserAccount> captor = ArgumentCaptor.forClass(UserAccount.class);
        when(repository.save(any(UserAccount.class))).thenAnswer(invocation -> {
            UserAccount user = invocation.getArgument(0);
            return new UserAccount(user.getName(), user.getEmail(), user.getPassword());
        });
        LoginResponse response = service.register(registerRequest);
        verify(repository).findByEmail("cristobal@example.com");
        verify(repository).save(captor.capture());
        UserAccount saved = captor.getValue();
        assertEquals("Cristobal", saved.getName());
        assertEquals("cristobal@example.com", saved.getEmail());
        assertEquals("123456", saved.getPassword());
        assertNotNull(response);
        assertEquals("Cristobal", response.getName());
        assertEquals("cristobal@example.com", response.getEmail());
    }

    @Test
    void givenExistingUser_whenLoginWithCorrectPassword_thenReturnsResponse() {
        UserAccount existing = new UserAccount("Cristobal", "cristobal@example.com", "123456");
        when(repository.findByEmail("cristobal@example.com")).thenReturn(Optional.of(existing));
        LoginRequest loginRequest = new LoginRequest() {
            public String getEmail() {
                return "cristobal@example.com";
            }
            public String getPassword() {
                return "123456";
            }
        };
        LoginResponse response = service.login(loginRequest);
        assertNotNull(response);
        assertEquals("Cristobal", response.getName());
        assertEquals("cristobal@example.com", response.getEmail());
    }

    @Test
    void givenExistingUser_whenLoginWithWrongPassword_thenReturnsNull() {
        UserAccount existing = new UserAccount("Cristobal", "cristobal@example.com", "123456");
        when(repository.findByEmail("cristobal@example.com")).thenReturn(Optional.of(existing));
        LoginRequest loginRequest = new LoginRequest() {
            public String getEmail() {
                return "cristobal@example.com";
            }
            public String getPassword() {
                return "000000";
            }
        };
        LoginResponse response = service.login(loginRequest);
        assertNull(response);
    }
}

