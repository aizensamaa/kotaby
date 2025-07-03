package com.graduation.Kotaby.repository;

import com.graduation.Kotaby.DTO.AyaResponse;

import java.util.List;

public interface WordsDAO {
    public List<String> getWordsByPage(int page);
    public List<AyaResponse> getAyatByPage(int page);
}
