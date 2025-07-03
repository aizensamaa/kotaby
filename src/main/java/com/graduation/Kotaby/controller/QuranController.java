package com.graduation.Kotaby.controller;

import com.graduation.Kotaby.DTO.AyaResponse;
import com.graduation.Kotaby.service.MemorizationService;
import com.graduation.Kotaby.service.WordsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/quran")
public class QuranController {
    private final WordsService wordsService;
    private final MemorizationService memorizationService;

    public QuranController(WordsService wordsService, MemorizationService memorizationService) {
        this.wordsService = wordsService;
        this.memorizationService = memorizationService;
    }
    @GetMapping("/page/{pageNumber}")
    public List<AyaResponse> getAyatByPage(@PathVariable int pageNumber) {
        return  wordsService.getAyatByPage(pageNumber);
    }

    @GetMapping("/progress/{userID}")
    public int getUserProgress(@PathVariable int userID) {
        return memorizationService.getUserProgress(userID);
    }
}
