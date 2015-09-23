package com.provectus.budgetrush.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.provectus.budgetrush.data.User;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByNameAndPassword(String name, String password);

    User findByName(String name);
}