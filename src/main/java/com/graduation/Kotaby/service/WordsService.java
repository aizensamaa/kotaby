package com.graduation.Kotaby.service;

import com.graduation.Kotaby.DTO.AyaResponse;

import java.util.List;

public interface WordsService {
    public List<String> getWordsByPage(int page);
    public List<AyaResponse> getAyatByPage(int page);
}
