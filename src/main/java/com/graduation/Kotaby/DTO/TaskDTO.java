package com.graduation.Kotaby.DTO;

import java.util.Date;

public class TaskDTO {
    private int id;
    private int userId;
    private int dayNumber;
    private int startPage;
    private int endPage;
    private Date taskDate;
    private int progress;
    private String surahRange;


    public TaskDTO() {}

    public TaskDTO(int id, int userId, int dayNumber, int startPage, int endPage,
                   Date taskDate, int progress) {
        this.id = id;
        this.userId = userId;
        this.dayNumber = dayNumber;
        this.startPage = startPage;
        this.endPage = endPage;
        this.taskDate = taskDate;
        this.progress = progress;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getDayNumber() { return dayNumber; }
    public void setDayNumber(int dayNumber) { this.dayNumber = dayNumber; }

    public int getStartPage() { return startPage; }
    public void setStartPage(int startPage) { this.startPage = startPage; }

    public int getEndPage() { return endPage; }
    public void setEndPage(int endPage) { this.endPage = endPage; }

    public Date getTaskDate() { return taskDate; }
    public void setTaskDate(Date taskDate) { this.taskDate = taskDate; }

    public int getProgress() { return progress; }
    public void setProgress(int progress) { this.progress = progress; }

    public String getSurahRange() { return surahRange; }
    public void setSurahRange(String surahRange) { this.surahRange = surahRange; }

    public boolean isCompleted() { return progress == 100; }
}