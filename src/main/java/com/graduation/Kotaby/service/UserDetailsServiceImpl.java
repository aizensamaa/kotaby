package com.graduation.Kotaby.service;

import com.graduation.Kotaby.entity.primary.UserDetails;
import com.graduation.Kotaby.repository.UserDetailsDAO;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional("primaryTransactionManager")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDetailsDAO userDetailsDAO;
    private final CloudinaryService cloudinaryService;

    @Autowired
    public UserDetailsServiceImpl(UserDetailsDAO userDetailsDAO, CloudinaryService cloudinaryService) {
        this.userDetailsDAO = userDetailsDAO;
        this.cloudinaryService = cloudinaryService;
    }

    @Override
    @Transactional
    public void saveUserDetails(UserDetails userDetails) {
        userDetailsDAO.saveUserDetails(userDetails);
    }

    @Override
    @Transactional
    public UserDetails getUserDetails(int id) {
        return userDetailsDAO.getUserDetails(id);
    }

    @Override
    @Transactional
    public void deleteUserDetails(int id) {
        userDetailsDAO.deleteUserDetails(id);
    }

    @Override
    @Transactional
    public void updateUserDetails(UserDetails userDetails) {
        userDetailsDAO.updateUserDetails(userDetails);
    }

    @Override
    @Transactional
    public List<UserDetails> getAllUsersDetails() {
        return userDetailsDAO.getAllUsersDetails();
    }

    @Override
    @Transactional
    public String updateProfilePhoto(Long userId, MultipartFile file) {
        String url = cloudinaryService.uploadImage(file);
        UserDetails userDetails = userDetailsDAO.getUserDetails(userId.intValue());
        userDetails.setImage(url);
        userDetailsDAO.updateUserDetails(userDetails);
        return url;
    }
}
