package com.graduation.Kotaby.repository;

import com.graduation.Kotaby.entity.primary.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDAOimpl implements UserDAO {
    @PersistenceContext(unitName = "primary")
    private final EntityManager entityManager;

    @Autowired
    public UserDAOimpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public User getUser(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void deleteUser(int id) {
        entityManager.remove(getUser(id));
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public void updateLastRead(int userId, int lastSurah, int lastAyah) {
        User user = getUser(userId);
        if (user != null) {
            user.setLastSurah(lastSurah);
            user.setLastAyah(lastAyah);
            entityManager.merge(user);
        }
    }

    @Override
    public List<User> getUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        try {
            User user = entityManager.createQuery("from User where email = :email", User.class)
                    .setParameter("email", email)
                    .getSingleResult();
            return Optional.of(user);
        } catch (NoResultException e) {
            return Optional.empty();  // Return empty Optional instead of throwing an exception
        }
    }

}
