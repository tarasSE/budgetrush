package com.provectus.budgetrush.service;

import com.provectus.budgetrush.data.User;
import com.provectus.budgetrush.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService extends GenericService <User, UserRepository> {

    @Autowired
    private UserRepository userRepository;

    @Override
    protected UserRepository getRepository() {
        return userRepository;
    }
}
