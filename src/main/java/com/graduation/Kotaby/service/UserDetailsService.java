package com.graduation.Kotaby.service;

import com.graduation.Kotaby.entity.primary.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserDetailsService {
    public void saveUserDetails(UserDetails userDetails);

    public UserDetails getUserDetails(int id);

    public void deleteUserDetails(int id);

    public void updateUserDetails(UserDetails userDetails);

    public List<UserDetails> getAllUsersDetails();

    String updateProfilePhoto(Long userId, MultipartFile file);
}
