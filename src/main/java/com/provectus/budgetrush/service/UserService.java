package com.provectus.budgetrush.service;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.provectus.budgetrush.data.Group;
import com.provectus.budgetrush.data.Roles;
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
    public User create(User user) {
        String hexPassword = DigestUtils.md5Hex(user.getPassword());
        user.setPassword(hexPassword);
        user.setRole(Roles.ROLE_USER);

        if (user.getGroups().isEmpty()) {
            Group group = new Group();
            group.setName("Main");
            Set<Group> groups = new HashSet<>();
            groups.add(group);
            user.setGroups(groups);
        }

        return super.create(user);
    }

    @Override
    public User update(User user, int id) {
        String hexPassword = DigestUtils.md5Hex(user.getPassword());
        user.setPassword(hexPassword);
        return super.update(user, id);
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
