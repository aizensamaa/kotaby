package com.graduation.Kotaby.service;

import com.graduation.Kotaby.entity.primary.Streak;

import java.util.Date;
import java.util.Optional;

public interface StreakService {
    void create(int userId);

    void update(int userId, Date date);

    Optional<Streak> getStreak(int userId);
}
