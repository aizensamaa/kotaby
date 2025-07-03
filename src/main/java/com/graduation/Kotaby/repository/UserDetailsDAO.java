package com.graduation.Kotaby.repository;

import com.graduation.Kotaby.entity.primary.UserDetails;

import java.util.List;

public interface UserDetailsDAO {
    public void saveUserDetails(UserDetails userDetails);

    public UserDetails getUserDetails(int id);

    public void deleteUserDetails(int id);

    public void updateUserDetails(UserDetails userDetails);

    public List<UserDetails> getAllUsersDetails();
}
