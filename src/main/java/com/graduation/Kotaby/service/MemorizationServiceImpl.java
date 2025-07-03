package com.graduation.Kotaby.service;

import com.graduation.Kotaby.entity.primary.Memorization;
import com.graduation.Kotaby.repository.MemorizationDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional("primaryTransactionManager")
public class MemorizationServiceImpl implements MemorizationService {
    private final MemorizationDAO memorizationDAO;

    public MemorizationServiceImpl(MemorizationDAO memorizationDAO) {
        this.memorizationDAO = memorizationDAO;
    }

    @Override
    @Transactional
    public void save(int userID, int page, int lastWord) {
        memorizationDAO.save(userID, page, lastWord);
    }

    @Override
    @Transactional
    public Optional<Memorization> getUserInfo(int userID, int page) {
        return memorizationDAO.getUserInfo(userID, page);
    }

    @Override
    @Transactional
    public int getLastWord(int userID, int page) {
        return memorizationDAO.getLastWord(userID, page);
    }

    @Override
    public int getUserProgress(int userID) {
        return memorizationDAO.getUserProgress(userID);
    }

    @Override
    @Transactional
    public void deleteInfo(int userID, int page) {
        memorizationDAO.deleteInfo(userID, page);
    }
}
