package com.example.rollandcodebackend.repository;

import com.example.rollandcodebackend.entity.UserGame;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserGameRepository extends JpaRepository<UserGame, Long> {

    List<UserGame> findByUserEmail(String userEmail);
}
