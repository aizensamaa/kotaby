package com.graduation.Kotaby.service;

import com.graduation.Kotaby.DTO.AyaResponse;
import com.graduation.Kotaby.repository.WordsDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional("secondaryTransactionManager")
public class WordsServiceImpl implements WordsService {
    private final WordsDAO wordsDAO;

    public WordsServiceImpl(WordsDAO wordsDAO) {
        this.wordsDAO = wordsDAO;
    }

    @Override
    public List<String> getWordsByPage(int page) {
        return wordsDAO.getWordsByPage(page);
    }

    @Override
    public List<AyaResponse> getAyatByPage(int page) {
        return wordsDAO.getAyatByPage(page);
    }
}
