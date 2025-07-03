package com.graduation.Kotaby.repository;

import com.graduation.Kotaby.entity.primary.Task;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskDAOImpl implements TaskDAO {
    @PersistenceContext(unitName = "primary")
    private EntityManager entityManager;

    @Autowired
    public TaskDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void saveTask(Task task) {
        entityManager.persist(task);
    }

    @Override
    public Task getTask(int id) {
        return entityManager.find(Task.class, id);
    }

    @Override
    public List<Task> getTasks() {
        return entityManager.createQuery("from Task", Task.class).getResultList();
    }

    @Override
    public List<Task> getTasksByUserId(int userId) {
        return entityManager.createQuery("from Task t where t.user.id = :userId", Task.class).setParameter("userId", userId).getResultList();
    }

    @Override
    public void deleteTask(int id) {
        entityManager.remove(getTask(id));
    }

    @Override
    public void deleteTasksByUserId(int userId) {
        entityManager.createQuery("DELETE FROM Task t WHERE t.user.id = :userId")
                .setParameter("userId", userId)
                .executeUpdate();
    }

    @Override
    public Task updateTask(Task task) {
        return entityManager.merge(task);
    }
}
