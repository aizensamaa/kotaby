package com.graduation.Kotaby.repository;

import com.graduation.Kotaby.entity.primary.Memorization;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MemorizationDAOImpl implements MemorizationDAO {
    @PersistenceContext(unitName = "primary")
    private final EntityManager entityManager;

    public MemorizationDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(int userID, int page, int lastWord) {
        Optional<Memorization> existingProgress = getUserInfo(userID, page);
        if (existingProgress.isPresent()) {
            Memorization memorization = existingProgress.get();
            memorization.setLastWord(lastWord);
            entityManager.merge(memorization);
        } else {
            Memorization memorization = new Memorization();
            memorization.setUserID(userID);
            memorization.setPage(page);
            memorization.setLastWord(lastWord);
            entityManager.persist(memorization);
        }
    }

    @Override
    public Optional<Memorization> getUserInfo(int userID, int page) {
        try {
            Memorization user = entityManager.createQuery("from Memorization where userID=:userID " +
                            "AND page=:page", Memorization.class)
                    .setParameter("userID", userID)
                    .setParameter("page", page)
                    .getSingleResult();
            return Optional.of(user);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public int getLastWord(int userID, int page) {
        Optional<Memorization> user = getUserInfo(userID, page);
        return user.map(Memorization::getLastWord).orElse(0);
    }

    @Override
    public void deleteInfo(int userID, int page) {
        entityManager.createQuery("delete from Memorization where userID=:userID " +
                        "AND page=:page", Memorization.class)
                .setParameter("userID", userID)
                .setParameter("page", page)
                .executeUpdate();
    }

    @Override
    public int getUserProgress(int userID) {
        Long sum= entityManager.createQuery("select sum(m.lastWord) from Memorization m where m.userID = :userID", Long.class)
                .setParameter("userID", userID)
                .getSingleResult();
        return sum != null ? sum.intValue() : 0;
    }
}
