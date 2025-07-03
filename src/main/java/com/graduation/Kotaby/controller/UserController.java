package com.graduation.Kotaby.controller;

import com.graduation.Kotaby.DTO.ResetPasswordRequest;
import com.graduation.Kotaby.DTO.UserDTO;
import com.graduation.Kotaby.entity.primary.Streak;
import com.graduation.Kotaby.entity.primary.User;
import com.graduation.Kotaby.entity.primary.UserDetails;
import com.graduation.Kotaby.service.StreakService;
import com.graduation.Kotaby.service.UserDetailsService;
import com.graduation.Kotaby.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserDetailsService userDetailsService;
    private final StreakService streakService;

    public UserController(UserService userService, UserDetailsService userDetailsService, StreakService streakService) {
        this.userService = userService;
        this.userDetailsService = userDetailsService;
        this.streakService = streakService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserDTO userDTO) {
        User user = userService.getUserByEmail(userDTO.getEmail());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "incorrect email or password"));
        }
        if (!user.getPassword().equals(userDTO.getPassword())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "incorrect email or password"));
        }
        UserDetails userDetails = userDetailsService.getUserDetails(user.getId().intValue());
        Streak streak = streakService.getStreak(user.getId().intValue())
                .orElseGet(() -> null);
        if (streak == null) {
            streakService.create(user.getId().intValue());
        }
        return ResponseEntity.ok(new UserDTO(user, userDetails));
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO) {
        userDTO.setId(null);
        System.out.println(userDTO.getEmail());
        User user = userService.getUserByEmail(userDTO.getEmail());
        UserDetails userDetails;
        if (user != null) {
            userDetails = userDetailsService.getUserDetails(user.getId().intValue());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new UserDTO(user, userDetails));
        }
        user = userDTO.toUser();
        userService.saveUser(user);
        userDTO.setId(user.getId());
        userDetails = userDTO.toUserDetails();
        userDetails.setUserId(user.getId());
        userDetailsService.saveUserDetails(userDetails);
        streakService.create(user.getId().intValue());
        return ResponseEntity.ok(new UserDTO(user, userDetails));
    }

    @PostMapping("/{userId}/update")
    public ResponseEntity<?> updateUser(@PathVariable int userId, @RequestBody User user) {
        User existingUser = userService.getUser(userId);
        if (existingUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "User not found"));
        }
        existingUser.setUserName(user.getUserName());
        existingUser.setPassword(user.getPassword());
        userService.saveUser(existingUser);
        UserDetails userDetails = userDetailsService.getUserDetails(userId);
        return ResponseEntity.ok(new UserDTO(existingUser, userDetails));
    }

    @GetMapping("")
    public List<UserDTO> getAllUsers() {
        List<User> users = userService.getUsers();
        List<UserDetails> userDetails = userDetailsService.getAllUsersDetails();
        List<UserDTO> userDTOs = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            userDTOs.add(new UserDTO(users.get(i), userDetails.get(i)));
        }
        return userDTOs;
    }

    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable int id) {
        User user = userService.getUser(id);
        UserDetails userDetails = userDetailsService.getUserDetails(id);
        return new UserDTO(user, userDetails);
    }

    @PostMapping("/{userId}/photo")
    public ResponseEntity<String> updateUserPhoto(@PathVariable Long userId, @RequestPart("file") MultipartFile file) {
        String imageUrl = userDetailsService.updateProfilePhoto(userId, file);
        return ResponseEntity.ok(imageUrl);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) {
        User user = userService.getUserByEmail(resetPasswordRequest.getEmail());
        if (user  == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "User not found"));
        }
        user.setPassword(resetPasswordRequest.getPassword());
        userService.saveUser(user);
        return ResponseEntity.ok("Password reset successfully");
    }

}
