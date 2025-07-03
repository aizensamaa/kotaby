package com.graduation.Kotaby.controller;

import com.graduation.Kotaby.DTO.HadithResponseDTO;
import com.graduation.Kotaby.service.HadithService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/hadith")
@CrossOrigin(origins = "*")
public class HadithController {

    private final HadithService hadithService;

    @Autowired
    public HadithController(HadithService hadithService) {
        this.hadithService = hadithService;
    }


    @GetMapping("/random")
    public ResponseEntity<HadithResponseDTO> getRandomHadiths() {
        try {
            HadithResponseDTO response = hadithService.getRandomHadiths();

            if (response.isSuccess()) {
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }

        } catch (Exception e) {
            HadithResponseDTO errorResponse = new HadithResponseDTO();
            errorResponse.setSuccess(false);
            errorResponse.setMessage("Failed to retrieve hadiths: " + e.getMessage());
            errorResponse.setData(null);
            errorResponse.setCount(0);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<HadithResponseDTO> searchHadiths(
            @RequestParam(value = "q", required = false, defaultValue = "") String searchQuery) {
        try {
            HadithResponseDTO response = hadithService.searchHadiths(searchQuery);

            if (response.isSuccess()) {
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }

        } catch (Exception e) {
            HadithResponseDTO errorResponse = new HadithResponseDTO();
            errorResponse.setSuccess(false);
            errorResponse.setMessage("Search failed: " + e.getMessage());
            errorResponse.setData(null);
            errorResponse.setCount(0);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }


    @PostMapping("/search")
    public ResponseEntity<HadithResponseDTO> searchHadithsPost(
            @RequestBody Map<String, String> requestBody) {
        try {
            String searchKey = requestBody.getOrDefault("searchKey", "");
            HadithResponseDTO response = hadithService.searchHadiths(searchKey);

            if (response.isSuccess()) {
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }

        } catch (Exception e) {
            HadithResponseDTO errorResponse = new HadithResponseDTO();
            errorResponse.setSuccess(false);
            errorResponse.setMessage("Search failed: " + e.getMessage());
            errorResponse.setData(null);
            errorResponse.setCount(0);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }


    @GetMapping("/health")
    public ResponseEntity<HadithResponseDTO> healthCheck() {
        HadithResponseDTO response = new HadithResponseDTO();
        response.setSuccess(true);
        response.setMessage("Hadith Service is running");
        response.setData(null);
        response.setCount(0);

        return ResponseEntity.ok(response);
    }
}