package com.graduation.Kotaby.entity.primary;

import jakarta.persistence.*;

@Entity
@Table(name = "memorization_progress")
public class Memorization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private int userID;

    @Column(name = "page")
    private int page;

    @Column(name = "last_word")
    private int lastWord;

    public Memorization() {
    }

    public Memorization(Long id, int userID, int page, int lastWord) {
        this.id = id;
        this.userID = userID;
        this.page = page;
        this.lastWord = lastWord;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLastWord() {
        return lastWord;
    }

    public void setLastWord(int lastWord) {
        this.lastWord = lastWord;
    }

    @Override
    public String toString() {
        return "Memorization{" +
                "id=" + id +
                ", userID=" + userID +
                ", page=" + page +
                ", lastWord=" + lastWord +
                '}';
    }
}
