package com.graduation.Kotaby.service;

import com.graduation.Kotaby.entity.primary.Streak;
import com.graduation.Kotaby.repository.StreakDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class StreakServiceImpl implements StreakService {
    private final StreakDAO streakDAO;

    public StreakServiceImpl(StreakDAO streakDAO) {
        this.streakDAO = streakDAO;
    }

    @Override
    @Transactional
    public void create(int userId) {
        streakDAO.create(userId);
    }

    @Override
    @Transactional
    public void update(int userId, Date date) {
        streakDAO.update(userId, date);
    }

    @Override
    @Transactional
    public Optional<Streak> getStreak(int userId) {
        return streakDAO.getStreak(userId);
    }
}
