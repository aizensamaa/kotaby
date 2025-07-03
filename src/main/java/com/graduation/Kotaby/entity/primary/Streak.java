package com.graduation.Kotaby.entity.primary;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "streak")
public class Streak {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "last_read")
    private Date lastRead;

    @Column(name = "current_streak")
    private int currentStreak;

    @Column(name = "max_streak")
    private int maxStreak;

    public Streak() {
    }

    public Streak(int id, int userId, Date lastRead, int currentStreak, int maxStreak) {
        this.id = id;
        this.userId = userId;
        this.lastRead = lastRead;
        this.currentStreak = currentStreak;
        this.maxStreak = maxStreak;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getLastRead() {
        return lastRead;
    }

    public void setLastRead(Date lastRead) {
        this.lastRead = lastRead;
    }

    public int getCurrentStreak() {
        return currentStreak;
    }

    public void setCurrentStreak(int currentStreak) {
        this.currentStreak = currentStreak;
    }

    public int getMaxStreak() {
        return maxStreak;
    }

    public void setMaxStreak(int maxStreak) {
        this.maxStreak = maxStreak;
    }

    @Override
    public String toString() {
        return "Streak{" +
                "id=" + id +
                ", userId=" + userId +
                ", lastRead=" + lastRead +
                ", currentStreak=" + currentStreak +
                ", maxStreak=" + maxStreak +
                '}';
    }
}
