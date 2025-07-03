package com.graduation.Kotaby.controller;

import com.graduation.Kotaby.DTO.StreakRequest;
import com.graduation.Kotaby.entity.primary.Streak;
import com.graduation.Kotaby.service.StreakService;
import com.graduation.Kotaby.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/streak")
public class StreakController {
    private final StreakService streakService;
    private final UserService userService;

    public StreakController(StreakService streakService, UserService userService) {
        this.streakService = streakService;
        this.userService = userService;
    }

    @PostMapping("/update/{userID}")
    public Streak updateStreak(@PathVariable int userID, @RequestBody(required = false) StreakRequest streakRequest) {
        Date date = streakRequest != null ? streakRequest.getDate() : null;
        int lastSurah = streakRequest != null ? streakRequest.getLastSurah() : 0;
        int lastAyah = streakRequest != null ? streakRequest.getLastAyah() : 0;
        if (date == null) {
            date = new Date();
        }
        streakService.update(userID, date);
        userService.updateLastRead(userID, lastSurah, lastAyah);
        return streakService.getStreak(userID)
                .orElseThrow(() -> new RuntimeException("Streak not found for user ID: " + userID));
    }

    @GetMapping("/{userID}")
    public Streak getStreak(@PathVariable int userID) {
        return streakService.getStreak(userID)
                .orElseThrow(() -> new RuntimeException("Streak not found for user ID: " + userID));
    }
}
