package com.graduation.Kotaby.service;

import com.graduation.Kotaby.DTO.TaskDTO;
import com.graduation.Kotaby.entity.primary.Task;

import java.util.List;

public interface TaskService {
    void saveTask(Task task);

    Task getTask(int id);

    List<Task> getTasksByUserId(int userId);

    List<Task> getTasks();

    void deleteTask(int id);

    List<TaskDTO> createQuranReadingPlan(int userId, int days);
    List<TaskDTO> getUserTasksAsDTO(int userId);
    TaskDTO completeTask(int taskId);
    TaskDTO updateTaskProgress(int taskId, int progress);
    void deleteUserTasks(int userId);
    boolean hasUserTasks(int userId);
    Task updateTask(Task task);
}
