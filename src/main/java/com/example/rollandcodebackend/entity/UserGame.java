package com.example.rollandcodebackend.entity;

import jakarta.persistence.*;

@Entity
public class UserGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String genre;
    private String platform;
    private int progress;
    private String progressDescription;
    private String userEmail;

    public UserGame() {
    }

    public UserGame(String name, String genre, String platform, int progress, String progressDescription, String userEmail) {
        this.name = name;
        this.genre = genre;
        this.platform = platform;
        this.progress = progress;
        this.progressDescription = progressDescription;
        this.userEmail = userEmail;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public String getPlatform() {
        return platform;
    }

    public int getProgress() {
        return progress;
    }

    public String getProgressDescription() {
        return progressDescription;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public void setProgressDescription(String progressDescription) {
        this.progressDescription = progressDescription;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}