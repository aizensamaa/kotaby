package com.graduation.Kotaby.repository;

import com.graduation.Kotaby.entity.primary.Streak;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

@Repository
public class StreakDAOImpl implements StreakDAO {
    @PersistenceContext(unitName = "primary")
    private final EntityManager entityManager;

    public StreakDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void create(int userId) {
        Streak streak = new Streak();
        streak.setUserId(userId);
        streak.setLastRead(new Date());
        streak.setCurrentStreak(1);
        streak.setMaxStreak(1);
        entityManager.persist(streak);
    }

    @Override
    public void update(int userId, Date date) {
        Streak streak = entityManager.createQuery("from Streak where userId=:userID", Streak.class)
                .setParameter("userID", userId)
                .getSingleResult();
        LocalDate currentDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate lastReadDate = streak.getLastRead().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        long dayDifference = ChronoUnit.DAYS.between(lastReadDate, currentDate);
        System.out.println("Current date: " + currentDate + ", Last read: " + lastReadDate +
                ", Day difference: " + dayDifference);
        if (dayDifference == 0) {
            return;
        } else if (dayDifference == 1) {
            streak.setCurrentStreak(streak.getCurrentStreak() + 1);
        } else {
            streak.setCurrentStreak(1);
        }
        streak.setLastRead(date);
        streak.setMaxStreak(Math.max(streak.getMaxStreak(), streak.getCurrentStreak()));
    }

    @Override
    public Optional<Streak> getStreak(int userId) {
        Streak streak = entityManager.createQuery("from Streak where userId=:userID", Streak.class)
                .setParameter("userID", userId)
                .getSingleResult();
        if (streak == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(streak);
    }
}
