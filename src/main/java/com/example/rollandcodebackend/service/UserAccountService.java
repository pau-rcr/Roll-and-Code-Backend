package com.example.rollandcodebackend.service;

import com.example.rollandcodebackend.dto.LoginRequest;
import com.example.rollandcodebackend.dto.LoginResponse;
import com.example.rollandcodebackend.dto.RegisterUserRequest;
import com.example.rollandcodebackend.entity.UserAccount;
import com.example.rollandcodebackend.repository.UserAccountRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAccountService {

    private final UserAccountRepository repository;

    public UserAccountService(UserAccountRepository repository) {
        this.repository = repository;
    }

    public LoginResponse register(RegisterUserRequest request) {
        Optional<UserAccount> existing = repository.findByEmail(request.getEmail());
        if (existing.isPresent()) {
            UserAccount user = existing.get();
            return new LoginResponse(user.getId(), user.getName(), user.getEmail());
        }
        UserAccount user = new UserAccount(
                request.getName(),
                request.getEmail(),
                request.getPassword()
        );
        UserAccount saved = repository.save(user);
        return new LoginResponse(saved.getId(), saved.getName(), saved.getEmail());
    }

    public LoginResponse login(LoginRequest request) {
        Optional<UserAccount> existing = repository.findByEmail(request.getEmail());
        if (existing.isEmpty()) {
            return null;
        }
        UserAccount user = existing.get();
        if (!user.getPassword().equals(request.getPassword())) {
            return null;
        }
        return new LoginResponse(user.getId(), user.getName(), user.getEmail());
    }
}
