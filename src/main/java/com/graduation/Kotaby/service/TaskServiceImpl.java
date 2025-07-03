package com.graduation.Kotaby.service;

import com.graduation.Kotaby.DTO.TaskDTO;
import com.graduation.Kotaby.entity.primary.Task;
import com.graduation.Kotaby.entity.primary.User;
import com.graduation.Kotaby.repository.TaskDAO;
import com.graduation.Kotaby.repository.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
@Transactional("primaryTransactionManager")
@Service
public class TaskServiceImpl implements TaskService {

    private final UserService userService;
    private final UserServiceImpl userServiceImpl;
    private TaskDAO taskDAO;
    private static final int TOTAL_QURAN_PAGES = 604;
    @Autowired
    public TaskServiceImpl(TaskDAO taskDAO, UserService userService, UserServiceImpl userServiceImpl) {
        this.taskDAO = taskDAO;
        this.userService = userService;
        this.userServiceImpl = userServiceImpl;
    }

    @Override
    public void saveTask(Task task) {
        taskDAO.saveTask(task);
    }

    @Override
    public Task getTask(int id) {
        return taskDAO.getTask(id);
    }

    @Override
    public List<Task> getTasksByUserId(int userId) {
        return taskDAO.getTasksByUserId(userId);
    }

    @Override
    public List<Task> getTasks() {
        return taskDAO.getTasks();
    }

    @Override
    public void deleteTask(int id) {
        taskDAO.deleteTask(id);
    }

    @Override
    public List<TaskDTO> createQuranReadingPlan(int userId, int days) {

        User user = userService.getUser(userId);
        if (user == null) {
            throw new RuntimeException("User not found with id: " + userId);
        }

        if (hasUserTasks(userId)) {
            deleteUserTasks(userId);
        }

        int pagesPerDay = TOTAL_QURAN_PAGES / days;
        int remainingPages = TOTAL_QURAN_PAGES % days;

        List<TaskDTO> taskDTOs = new ArrayList<>();
        int currentPage = 1;
        Calendar calendar = Calendar.getInstance();

        for (int day = 1; day <= days; day++) {
            int pagesToRead = pagesPerDay;

            if (day <= remainingPages) {
                pagesToRead++;
            }

            int startPage = currentPage;
            int endPage = currentPage + pagesToRead - 1;

            Task task = new Task();
            task.setUser(user);
            task.setStartPage(startPage);
            task.setEndPage(endPage);
            task.setTaskDate(new Date(calendar.getTimeInMillis()));
            task.setProgress(0);

            taskDAO.saveTask(task);


            TaskDTO taskDTO = convertToDTO(task, day);
            taskDTOs.add(taskDTO);

            currentPage = endPage + 1;
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        return taskDTOs;
    }
    @Override
    public List<TaskDTO> getUserTasksAsDTO(int userId) {
        List<Task> tasks = taskDAO.getTasksByUserId(userId);
        return tasks.stream()
                .map(task -> {
                    int dayNumber = tasks.indexOf(task) + 1;
                    return convertToDTO(task, dayNumber);
                })
                .collect(Collectors.toList());
    }

    @Override
    public TaskDTO completeTask(int taskId) {
        Task task = taskDAO.getTask(taskId);
        if (task == null) {
            throw new RuntimeException("Task not found with id: " + taskId);
        }

        task.setProgress(100);
        Task updatedTask = updateTask(task);

        List<Task> userTasks = taskDAO.getTasksByUserId(task.getUser().getId().intValue());
        int dayNumber = userTasks.indexOf(updatedTask) + 1;

        return convertToDTO(updatedTask, dayNumber);
    }

    @Override
    public TaskDTO updateTaskProgress(int taskId, int progress) {
        Task task = taskDAO.getTask(taskId);
        if (task == null) {
            throw new RuntimeException("Task not found with id: " + taskId);
        }

        if (progress < 0 || progress > 100) {
            throw new RuntimeException("Progress must be between 0 and 100");
        }

        task.setProgress(progress);
        Task updatedTask = updateTask(task);


        List<Task> userTasks = taskDAO.getTasksByUserId(task.getUser().getId().intValue());
        int dayNumber = userTasks.indexOf(updatedTask) + 1;

        return convertToDTO(updatedTask, dayNumber);
    }

    @Override
    public void deleteUserTasks(int userId) {
        taskDAO.deleteTasksByUserId(userId);
    }

    @Override
    public boolean hasUserTasks(int userId) {
        List<Task> tasks = taskDAO.getTasksByUserId(userId);
        return !tasks.isEmpty();
    }

    @Override
    public Task updateTask(Task task) {
        return taskDAO.updateTask(task);
    }

    private TaskDTO convertToDTO(Task task, int dayNumber) {
        TaskDTO dto = new TaskDTO();
        dto.setId(task.getId());
        dto.setUserId(task.getUser().getId().intValue());
        dto.setDayNumber(dayNumber);
        dto.setStartPage(task.getStartPage());
        dto.setEndPage(task.getEndPage());
        dto.setTaskDate(task.getTaskDate());
        dto.setProgress(task.getProgress());


        dto.setSurahRange("Pages " + task.getStartPage() + " - " + task.getEndPage());

        return dto;
    }
}

