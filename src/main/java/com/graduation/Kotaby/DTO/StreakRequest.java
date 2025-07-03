package com.graduation.Kotaby.DTO;

import java.util.Date;

public class StreakRequest {
    private Date date;
    private Integer lastSurah;
    private Integer lastAyah;

    public StreakRequest() {
    }

    public StreakRequest(Date date, Integer lastSurah, Integer lastAyah) {
        this.date = date;
        this.lastSurah = lastSurah;
        this.lastAyah = lastAyah;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getLastSurah() {
        return lastSurah;
    }

    public void setLastSurah(Integer lastSurah) {
        this.lastSurah = lastSurah;
    }

    public Integer getLastAyah() {
        return lastAyah;
    }

    public void setLastAyah(Integer lastAyah) {
        this.lastAyah = lastAyah;
    }
}
