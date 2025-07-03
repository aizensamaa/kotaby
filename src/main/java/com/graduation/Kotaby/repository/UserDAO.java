package com.graduation.Kotaby.repository;

import com.graduation.Kotaby.entity.primary.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {
    public void saveUser(User user);

    public User getUser(int id);

    public Optional<User> getUserByEmail(String email);

    public List<User> getUsers();

    public void updateUser(User user);

    public void updateLastRead(int userId, int lastSurah, int lastAyah);

    public void deleteUser(int id);
}
