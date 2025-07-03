package com.graduation.Kotaby.controller;

import com.graduation.Kotaby.service.CheckerService;
import com.graduation.Kotaby.service.MemorizationService;
import com.graduation.Kotaby.service.ModelService;
import com.graduation.Kotaby.service.WordsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/memo")
public class ModelController {
    private final ModelService modelService;
    private final WordsService wordsService;
    private final CheckerService checkerService;
    private final MemorizationService memorizationService;

    public ModelController(ModelService modelService, WordsService wordsService, CheckerService checkerService, MemorizationService memorizationService) {
        this.modelService = modelService;
        this.wordsService = wordsService;
        this.checkerService = checkerService;
        this.memorizationService = memorizationService;
    }

    @GetMapping("/test-cors")
    public ResponseEntity<Map<String, String>> testCorsEndpoint() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "CORS is working!");
        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/transcribe",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> transcribeAudio(
            @RequestParam("file") MultipartFile audioFile,
            @RequestParam("id") int userID,
            @RequestParam("page") int page) {
        try {
            System.out.println("Working");
            String text = modelService.audioToText(audioFile);
            System.out.println(text);
            List<String> userWords = Arrays.asList(text.split(" "));
            List<String> pageWords = wordsService.getWordsByPage(page);
            
            List<String> result = checkerService.check(userWords, pageWords, userID, page);
            
            Map<String, Object> response = new HashMap<>();
            response.put("transcription", text);
            response.put("result", result);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Error processing audio file: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body(errorResponse);
        }
    }

    @PostMapping(path = "/check")
    public ResponseEntity<?> transcribeAudio(@RequestParam String transcribedAudio,
                                             @RequestParam int userID,
                                             @RequestParam int page) {
        try {
            List<String> userWords = Arrays.asList(transcribedAudio.split(" "));
            List<String> pageWords = wordsService.getWordsByPage(page);

            List<String> result = checkerService.check(userWords, pageWords, userID, page);

            Map<String, Object> response = new HashMap<>();
            response.put("transcription", transcribedAudio);
            response.put("result", result);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Error processing audio file: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(errorResponse);
        }
    }

    @PostMapping(path = "/save")
    public ResponseEntity<?> saveWork(@RequestParam("id") int userID,
                                      @RequestParam("page") int page,
                                      @RequestParam("last") int lastWord) {
        try {
            memorizationService.save(userID, page, lastWord);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Work saved successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Error saving work: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body(errorResponse);
        }
    }
}

