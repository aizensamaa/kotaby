package com.graduation.Kotaby.repository;

import com.graduation.Kotaby.entity.primary.UserDetails;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class UserDetailsDAOimpl implements UserDetailsDAO {
    @PersistenceContext(unitName = "primary")
    private final EntityManager entityManager;

    @Autowired
    public UserDetailsDAOimpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public void saveUserDetails(UserDetails userDetails) {
        entityManager.persist(userDetails);
    }

    @Override
    public UserDetails getUserDetails(int id) {
        return entityManager.find(UserDetails.class, id);
    }

    @Override
    public void deleteUserDetails(int id) {
        entityManager.remove(getUserDetails(id));
    }

    @Override
    public void updateUserDetails(UserDetails userDetails) {
        entityManager.merge(userDetails);
    }

    @Override
    public List<UserDetails> getAllUsersDetails() {
        return entityManager.createQuery(
                        "from UserDetails", UserDetails.class).
                getResultList();
    }
}
