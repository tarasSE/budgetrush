package com.provectus.budgetrush.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.provectus.budgetrush.data.User;
import com.provectus.budgetrush.repository.UserRepository;

@Service
@Transactional
public class UserService extends GenericService<User, UserRepository> {

    @Autowired
    private UserRepository userRepository;

    @Override
    protected UserRepository getRepository() {
        return userRepository;
    }

    public User find(String name, String password) {
        return getRepository().findByNameAndPassword(name, password);
    }

}
