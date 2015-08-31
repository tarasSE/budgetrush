package com.provectus.budgetrush.services;

import com.provectus.budgetrush.data.User;

import java.util.List;

/**
 * Created by taras on 28.08.15.
 */
public interface UserService {
    User addUser(User userDTO);

    void delete(int id);

    User getById(int id);

    User getByName(String name);

    User editUser(User userDTO);

    List<User> getAll();
}
