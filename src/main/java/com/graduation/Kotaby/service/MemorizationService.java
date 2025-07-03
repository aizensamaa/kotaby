package com.graduation.Kotaby.service;

import com.graduation.Kotaby.entity.primary.Memorization;

import java.util.Optional;

public interface MemorizationService {
    void save(int userID, int page, int lastWord);

    Optional<Memorization> getUserInfo(int userID, int page);

    int getLastWord(int userID, int page);

    int getUserProgress(int userID);

    void deleteInfo(int userID, int page);
}
