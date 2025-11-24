package com.example.rollandcodebackend.controller;

import com.example.rollandcodebackend.dto.UserGameRequest;
import com.example.rollandcodebackend.entity.UserGame;
import com.example.rollandcodebackend.service.UserGameService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usergames")
@CrossOrigin("*")
public class UserGameController {

    private final UserGameService service;

    public UserGameController(UserGameService service) {
        this.service = service;
    }

    @GetMapping
    public List<UserGame> getAll() {
        return service.getAll();
    }

    @PostMapping
    public UserGame create(@RequestBody UserGameRequest request) {
        return service.create(request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
