package com.provectus.budgetrush.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.provectus.budgetrush.data.User;
import com.provectus.budgetrush.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/v1/users", headers = "Accept=application/json")
public class UserController {

    @Autowired
    private UserService service;

    @PostAuthorize("adminOnly()")
    @RequestMapping(method = GET)
    @ResponseBody
    public List<User> listAll() {
        log.info("Send all users");
        return service.getAll();
    }

    @RequestMapping(value = "/{id}", method = GET)
    @ResponseBody
    public User getById(@PathVariable Integer id) {

        log.info("Send user by id " + id);
        User user = service.getById(id);
        return user;

    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/role/{name}", method = GET)
    @ResponseBody
    public String getRole(@PathVariable String name) {

        log.info("Send user role by name " + name);

        return service.getRoleByName(name).toString();

    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/{id}", method = DELETE)
    @ResponseBody
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    @PreAuthorize("isAnonymous()")
    @RequestMapping(method = POST)
    @ResponseBody
    public User newUser(@RequestBody User user) {
        log.info("Save user " + user.getName());
        user.setId(0);
        return service.createOrUpdate(user);

    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/{id}", method = PUT)
    @ResponseBody
    public User saveUser(@RequestBody User user, @PathVariable Integer id) {
        log.info("Save user " + user.getName());
        user.setId(id);
        return service.createOrUpdate(user);
    }

}
