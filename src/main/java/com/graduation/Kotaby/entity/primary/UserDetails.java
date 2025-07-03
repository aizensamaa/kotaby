package com.graduation.Kotaby.entity.primary;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "user_details")
public class UserDetails {
    @Id
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "user_photo")
    private String image;
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    @Column(name = "nationality")
    private String nationality;

    public UserDetails() {
    }

    public UserDetails(String image, Date dateOfBirth, String nationality) {
        this.image = image;
        this.dateOfBirth = dateOfBirth;
        this.nationality = nationality;
    }

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    @Override
    public String toString() {
        return "UserDetails{" + "userId=" + userId + ", image='" + image + '\'' + ", dateOfBirth=" + dateOfBirth + ", Nationality=" + nationality;
    }
}
