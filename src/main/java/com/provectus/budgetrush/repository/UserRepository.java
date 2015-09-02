package com.provectus.budgetrush.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.provectus.budgetrush.data.User;

/**
 * Created by taras on 28.08.15.
 */
@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByName(String name);
}