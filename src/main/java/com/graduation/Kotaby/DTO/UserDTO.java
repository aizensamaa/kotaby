package com.graduation.Kotaby.DTO;

import com.graduation.Kotaby.entity.primary.User;
import com.graduation.Kotaby.entity.primary.UserDetails;

import java.util.Date;

public class UserDTO {

    private Long id;
    private String userName;
    private String password;
    private String email;
    private String image;
    private Date dateOfBirth;
    private String nationality;
    private int lastSurah;
    private int lastAyah;

    public UserDTO() {
    }

    public UserDTO(User user, UserDetails userDetails) {
        this.id = user.getId();
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.image = userDetails.getImage();
        this.dateOfBirth = userDetails.getDateOfBirth();
        this.nationality = userDetails.getNationality();
        this.lastSurah = user.getLastSurah();
        this.lastAyah = user.getLastAyah();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public int getLastSurah() {
        return lastSurah;
    }

    public void setLastSurah(int lastSurah) {
        this.lastSurah = lastSurah;
    }

    public int getLastAyah() {
        return lastAyah;
    }

    public void setLastAyah(int lastAyah) {
        this.lastAyah = lastAyah;
    }

    public User toUser() {
        User user = new User();
        user.setId(id);
        user.setUserName(userName);
        user.setPassword(password);
        user.setEmail(email);
        return user;
    }

    public UserDetails toUserDetails() {
        UserDetails userDetails = new UserDetails();
        userDetails.setImage(image);
        userDetails.setDateOfBirth(dateOfBirth);
        userDetails.setNationality(nationality);
        return userDetails;
    }
}
