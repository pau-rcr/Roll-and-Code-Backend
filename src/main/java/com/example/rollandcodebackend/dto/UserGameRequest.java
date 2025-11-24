package com.example.rollandcodebackend.dto;


public class UserGameRequest {

    private String name;
    private String genre;
    private String platform;
    private int progress;
    private String progressDescription;
    private String userEmail;

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
}
