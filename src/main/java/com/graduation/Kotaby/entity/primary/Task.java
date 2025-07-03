package com.graduation.Kotaby.entity.primary;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "start_page")
    private int startPage;

    @Column(name = "end_page")
    private int endPage;

    @Column(name = "task_date")
    private Date taskDate;

    @Column(name = "progress")
    private int progress;

    public Task(User user, int startPage, int endPage, Date taskDate, int progress) {
        this.user = user;
        this.startPage = startPage;
        this.endPage = endPage;
        this.taskDate = taskDate;
        this.progress = progress;
    }

    public Task() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public Date getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(Date taskDate) {
        this.taskDate = taskDate;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    @Override
    public String toString() {
        return "Task{" + "id=" + id + ", user=" + user + ", startPage=" + startPage + ", endPage=" + endPage + ", taskDate=" + taskDate + ", progress=" + progress + '}';
    }
}
