package com.provectus.budgetrush.repository;

import com.provectus.budgetrush.data.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by taras on 28.08.15.
 */
//Annotate accordingly
public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByName(String name);
}