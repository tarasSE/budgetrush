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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.provectus.budgetrush.data.User;
import com.provectus.budgetrush.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
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
    public String getRole(@PathVariable String name, @PathVariable String password) {

        log.info("Send user role by name " + name);
        String role = service.getRoleByName(name).toString();
        return "{\"role\"" + ":" + "\"" + role + "\"}";

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
    public String newUser(@RequestBody User user) {
        ObjectMapper om = new ObjectMapper();
        log.info("Save user " + user.getName());
        user.setId(0);
        try {
            return om.writeValueAsString(service.createOrUpdate(user));
        } catch (DataIntegrityViolationException ex) {
            return "{\"exception\" : \"User already exist.\"}";
        } catch (JsonProcessingException ex) {
            return "{\"exception\" : \"JSON processing exception.\"}";
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
