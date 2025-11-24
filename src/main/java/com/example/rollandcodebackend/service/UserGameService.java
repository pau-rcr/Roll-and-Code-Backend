package com.example.rollandcodebackend.service;

import com.example.rollandcodebackend.dto.UserGameRequest;
import com.example.rollandcodebackend.entity.UserGame;
import com.example.rollandcodebackend.repository.UserGameRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserGameService {

    private final UserGameRepository repository;

    public UserGameService(UserGameRepository repository) {
        this.repository = repository;
    }

    public List<UserGame> getGamesByUserEmail(String email) {
        return repository.findByUserEmail(email);
    }

    public UserGame createGame(UserGameRequest request) {
        UserGame game = new UserGame(
                request.getName(),
                request.getGenre(),
                request.getPlatform(),
                request.getProgress(),
                request.getProgressDescription(),
                request.getUserEmail()
        );
        return repository.save(game);
    }

    public void deleteGame(Long id) {
        repository.deleteById(id);
    }
}
