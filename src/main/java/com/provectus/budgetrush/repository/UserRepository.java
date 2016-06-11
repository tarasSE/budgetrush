package com.provectus.budgetrush.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.provectus.budgetrush.data.user.User;

@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByNameAndPassword(String name, String password);

    User findByName(String name);

    User findByEmail(String email);
}