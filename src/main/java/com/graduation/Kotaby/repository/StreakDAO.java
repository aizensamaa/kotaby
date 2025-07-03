package com.graduation.Kotaby.repository;

import com.graduation.Kotaby.entity.primary.Streak;

import java.util.Date;
import java.util.Optional;

public interface StreakDAO {

    void create(int userId);

    void update(int userId, Date date);

    Optional<Streak> getStreak(int userId);
}
