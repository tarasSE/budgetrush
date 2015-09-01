package com.provectus.budgetrush.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.provectus.budgetrush.data.User;
import com.provectus.budgetrush.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by taras on 28.08.15.
 */
@Slf4j
@Service
public class UserServiceBean implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User addUser(User user) {
        log.info("Start tosave user " + user.getName());
        return userRepository.saveAndFlush(user);
    }

    @Override
    public void delete(int id) {
        userRepository.delete(id);
    }

    @Override
    public User getById(int id) {
        return userRepository.findOne(id);
    }

    @Override
    public User getByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public User editUser(User userDTO) {
        return userRepository.saveAndFlush(userDTO);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
}
