package com.provectus.budgetrush.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.provectus.budgetrush.data.User;
import com.provectus.budgetrush.repository.UserRepository;

@Service
@Transactional
public class UserService extends GenericService<User, UserRepository> {

    @Qualifier("userRepository")
    @Autowired
    private UserRepository userRepository;

    @Override
    protected UserRepository getRepository() {
        return userRepository;
    }
}
