package com.graduation.Kotaby.controller;

import com.graduation.Kotaby.DTO.TaskDTO;
import com.graduation.Kotaby.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "*")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/create-reading-plan")
    public ResponseEntity<List<TaskDTO>> createReadingPlan(
            @RequestParam int userId,
            @RequestParam int days) {
        try {
            if (days <= 0) {
                return ResponseEntity.badRequest().build();
            }

            List<TaskDTO> tasks = taskService.createQuranReadingPlan(userId, days);
            return ResponseEntity.ok(tasks);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TaskDTO>> getUserTasks(@PathVariable int userId) {
        try {
            List<TaskDTO> tasks = taskService.getUserTasksAsDTO(userId);
            return ResponseEntity.ok(tasks);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{taskId}/complete")
    public ResponseEntity<TaskDTO> completeTask(@PathVariable int taskId) {
        try {
            TaskDTO task = taskService.completeTask(taskId);
            return ResponseEntity.ok(task);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{taskId}/progress")
    public ResponseEntity<TaskDTO> updateTaskProgress(
            @PathVariable int taskId,
            @RequestParam int progress) {
        try {
            TaskDTO task = taskService.updateTaskProgress(taskId, progress);
            return ResponseEntity.ok(task);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Void> deleteUserTasks(@PathVariable int userId) {
        try {
            taskService.deleteUserTasks(userId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/user/{userId}/summary")
    public ResponseEntity<TaskSummary> getTaskSummary(@PathVariable int userId) {
        try {
            List<TaskDTO> tasks = taskService.getUserTasksAsDTO(userId);

            int totalTasks = tasks.size();
            int completedTasks = (int) tasks.stream().filter(TaskDTO::isCompleted).count();
            int totalPages = tasks.stream().mapToInt(t -> t.getEndPage() - t.getStartPage() + 1).sum();
            double completionPercentage = totalTasks > 0 ? (double) completedTasks / totalTasks * 100 : 0;

            TaskSummary summary = new TaskSummary(totalTasks, completedTasks,
                    totalTasks - completedTasks, totalPages, completionPercentage);

            return ResponseEntity.ok(summary);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public static class TaskSummary {
        private int totalTasks;
        private int completedTasks;
        private int remainingTasks;
        private int totalPages;
        private double completionPercentage;

        public TaskSummary(int totalTasks, int completedTasks, int remainingTasks,
                           int totalPages, double completionPercentage) {
            this.totalTasks = totalTasks;
            this.completedTasks = completedTasks;
            this.remainingTasks = remainingTasks;
            this.totalPages = totalPages;
            this.completionPercentage = completionPercentage;
        }

        public int getTotalTasks() { return totalTasks; }
        public int getCompletedTasks() { return completedTasks; }
        public int getRemainingTasks() { return remainingTasks; }
        public int getTotalPages() { return totalPages; }
        public double getCompletionPercentage() { return completionPercentage; }

        public void setTotalTasks(int totalTasks) { this.totalTasks = totalTasks; }
        public void setCompletedTasks(int completedTasks) { this.completedTasks = completedTasks; }
        public void setRemainingTasks(int remainingTasks) { this.remainingTasks = remainingTasks; }
        public void setTotalPages(int totalPages) { this.totalPages = totalPages; }
        public void setCompletionPercentage(double completionPercentage) { this.completionPercentage = completionPercentage; }
    }
}
