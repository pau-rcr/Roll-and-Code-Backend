package com.example.rollandcodebackend.repository;

import com.example.rollandcodebackend.entity.UserGame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGameRepository extends JpaRepository<UserGame, Long> {
}
