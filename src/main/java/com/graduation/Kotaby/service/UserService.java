package com.graduation.Kotaby.service;

import com.graduation.Kotaby.entity.primary.User;

import java.util.List;

public interface UserService {
    public void saveUser(User user);

    public User getUser(int id);

    public void deleteUser(int id);

    public void updateUser(User user);

    public void updateLastRead(int userId, int lastSurah, int lastAyah);

    public List<User> getUsers();

    public User getUserByEmail(String email);
}
