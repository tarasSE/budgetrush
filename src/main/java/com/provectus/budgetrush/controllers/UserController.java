package com.provectus.budgetrush.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Preconditions;
import com.provectus.budgetrush.data.Roles;
import com.provectus.budgetrush.data.User;
import com.provectus.budgetrush.exceptions.CustomException;
import com.provectus.budgetrush.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@PreAuthorize("hasRole('ROLE_USER')")
@RequestMapping(value = "/v1/users", headers = "Accept=application/json")
public class UserController {

    @Autowired
    private UserService service;

    @PostFilter("isObjectOwnerOrAdmin(filterObject, 'read')")
    @RequestMapping(method = GET)
    @ResponseBody
    public List<User> listAll() {
        log.info("Send all users");
        return service.getAll();
    }

    @PostAuthorize("isObjectOwnerOrAdmin(returnObject, 'read')")
    @RequestMapping(value = "/{id}", method = GET)
    @ResponseBody
    public User getById(@PathVariable Integer id) {
        log.info("Send user by id " + id);
        User user = service.getById(id);
        return user;

    }

    @PreAuthorize("adminOnly() or #name == authentication.name")
    @RequestMapping(value = "/role/{name}", method = GET)
    @ResponseBody
    public String getRole(@PathVariable String name) {

        log.info("Send user role by name " + name);
        String role = service.getRoleByName(name).toString();
        return "{\"role\"" + ":" + "\"" + role + "\"}";

    }

    @PreAuthorize("adminOnly()")
    @RequestMapping(value = "/role/{name}&{strRole}", method = PUT)
    @ResponseBody
    public void setRole(@PathVariable String name, @PathVariable String strRole) {

        strRole = strRole.toUpperCase();
        log.info("Set user role " + strRole + " by name " + name);
        User user = service.find(name);
        Preconditions.checkNotNull(user, "User not found.");
        Roles role = Roles.valueOf(strRole);
        user.setRole(role);
        service.createOrUpdate(user);

    }

    @PreAuthorize("adminOnly()")
    @RequestMapping(value = "/{id}", method = DELETE)
    @ResponseBody
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    @PreAuthorize("adminOnly() or isAnonymous()")
    @RequestMapping(method = POST)
    @ResponseBody
    public User newUser(@RequestBody User user) {
        log.info("Save user " + user.getName());
        user.setId(0);
        user.setRole(Roles.ROLE_USER);

        try {
            return service.createOrUpdate(user);
        } catch (DataIntegrityViolationException exception) {
            throw new CustomException("User already exist.");
        }

    }

    @PreAuthorize("isObjectOwnerOrAdmin(#user, 'wright')")
    @RequestMapping(value = "/{id}", method = PUT)
    @ResponseBody
    public User saveUser(@RequestBody User user, @PathVariable Integer id) {
        log.info("Save user " + user.getName());
        user.setId(id);
        return service.createOrUpdate(user);
    }

}
