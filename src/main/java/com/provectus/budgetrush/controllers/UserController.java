package com.provectus.budgetrush.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.provectus.budgetrush.data.User;
import com.provectus.budgetrush.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(value = "/v1/users", headers = "Accept=application/json")
@Controller
public class UserController {

    @Autowired
    private UserService service;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<User> listAll() {
        log.info("Send all users");
        return service.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public User getById(@PathVariable Integer id) {

        log.info("Send user by id " + id);
        User user = service.getById(id);
        return user;

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public User newUser(@RequestBody User user) {
        log.info("Save user " + user.getName());
        user.setId(0);
        return service.createOrUpdate(user);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public User saveUser(@RequestBody User user, @PathVariable Integer id) {
        log.info("Save user " + user.getName());
        user.setId(id);
        return service.createOrUpdate(user);
    }

}
