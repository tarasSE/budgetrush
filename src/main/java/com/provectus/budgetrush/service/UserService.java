package com.provectus.budgetrush.service;

import java.util.HashSet;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.provectus.budgetrush.data.Group;
import com.provectus.budgetrush.data.Roles;
import com.provectus.budgetrush.data.User;
import com.provectus.budgetrush.repository.UserRepository;

@Slf4j
@Service
@Repository
public class UserService extends GenericService<User, UserRepository> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupService groupService;

    @Override
    protected UserRepository getRepository() {
        return userRepository;
    }

    @Override
    @Transactional
    public User create(User user) {
        String hexPassword = DigestUtils.md5Hex(user.getPassword());
        user.setPassword(hexPassword);
        user.setRole(Roles.ROLE_USER);

        User creatUser = super.create(user);

        if (creatUser.getGroups() == null || creatUser.getGroups().isEmpty()) {
            log.info("Add main group to new user");
            Group group = new Group();
            group.setName("Main");
            Set<User> users = new HashSet<>();
            users.add(creatUser);
            group.setUsers(users);
            groupService.create(group);

        }

        return creatUser;
    }

    @Override
    @Transactional
    public User update(User user, int id) {
        String hexPassword = DigestUtils.md5Hex(user.getPassword());
        user.setPassword(hexPassword);
        return super.update(user, id);
    }

    @Transactional
    public User find(String name, String password) {
        User user = getRepository().findByNameAndPassword(name, password);
        if (user == null) {
            throw new ResourceNotFoundException("User");
        }
        return user;
    }

    @Transactional
    public User find(String name) {
        User user = getRepository().findByName(name);
        if (user == null) {
            throw new ResourceNotFoundException("User");
        }
        return user;
    }

    @Transactional
    public Enum<?> getRoleByName(String name) {
        User user = getRepository().findByName(name);
        return user.getRole();
    }

    @Transactional
    public Set<Group> getUserGroups(int id) {
        User user = getRepository().getOne(id);
        return user.getGroups();
    }
}
