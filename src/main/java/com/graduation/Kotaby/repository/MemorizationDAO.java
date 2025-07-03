package com.graduation.Kotaby.repository;

import com.graduation.Kotaby.entity.primary.Memorization;

import java.util.Optional;

public interface MemorizationDAO {
    void save(int userID, int page, int lastWord);

    Optional<Memorization> getUserInfo(int userID, int page);

    int getLastWord(int userID, int page);

    void deleteInfo(int userID, int page);

    int getUserProgress(int userID);
}
