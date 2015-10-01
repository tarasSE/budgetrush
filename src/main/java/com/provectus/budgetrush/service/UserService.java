package com.provectus.budgetrush.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.velocity.exception.ResourceNotFoundException;
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

    @Override
    public User createOrUpdate(User user) {
        String hexPassword = DigestUtils.md5Hex(user.getPassword());
        if (user.getId() != 0) {
            user.setRole(getById(user.getId()).getRole());
        }
        user.setPassword(hexPassword);
        return getRepository().saveAndFlush(user);
    }

    public User find(String name, String password) {
        User user = getRepository().findByNameAndPassword(name, password);
        if (user == null) {
            throw new ResourceNotFoundException("User");
        }
        return user;
    }

    public User find(String name) {
        User user = getRepository().findByName(name);
        if (user == null) {
            throw new ResourceNotFoundException("User");
        }
        return user;
    }

    public Enum<?> getRoleByName(String name) {
        User user = getRepository().findByName(name);
        return user.getRole();
    }
}
