package com.graduation.Kotaby.repository;

import com.graduation.Kotaby.entity.primary.Task;

import java.util.List;

public interface TaskDAO {
    void saveTask(Task task);

    Task getTask(int id);

    List<Task> getTasksByUserId(int userId);

    List<Task> getTasks();

    void deleteTask(int id);

    void deleteTasksByUserId(int userId);

    Task updateTask(Task task);
}
