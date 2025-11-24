package com.example.rollandcodebackend.controller;

import com.example.rollandcodebackend.dto.UserGameRequest;
import com.example.rollandcodebackend.entity.UserGame;
import com.example.rollandcodebackend.service.UserGameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usergames")
@CrossOrigin
public class UserGameController {

    private final UserGameService service;

    public UserGameController(UserGameService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<UserGame>> getUserGames(@RequestParam("email") String email) {
        List<UserGame> games = service.getGamesByUserEmail(email);
        return ResponseEntity.ok(games);
    }

    @PostMapping
    public ResponseEntity<UserGame> createUserGame(@RequestBody UserGameRequest request) {
        UserGame created = service.createGame(request);
        return ResponseEntity.ok(created);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserGame(@PathVariable Long id) {
        service.deleteGame(id);
        return ResponseEntity.noContent().build();
    }
}
