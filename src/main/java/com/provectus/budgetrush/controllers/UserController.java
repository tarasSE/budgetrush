package com.provectus.budgetrush.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.provectus.budgetrush.data.User;
import com.provectus.budgetrush.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Slf4j
@Controller
@RequestMapping(value = "/v1/users", headers = "Accept=application/json")
public class UserController {

    @Autowired
    private UserService service;

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

    @RequestMapping(value = "/role/{name}&{password}", method = GET)
    @ResponseBody
    public String getRole(@PathVariable String name,@PathVariable String password ) {

        log.info("Send user role by name " + name);
        String role = service.getRoleByNameAndPassword(name, password).toString();
        return "{\"role\"" + ":" + "\"" + role + "\"}";

    }


    @RequestMapping(value = "/{id}", method = DELETE)
    @ResponseBody
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    @RequestMapping(method = POST)
    @ResponseBody
    public String newUser(@RequestBody User user) {
        ObjectMapper om = new ObjectMapper();
        log.info("Save user " + user.getName());
        user.setId(0);
        try {
            return om.writeValueAsString(service.createOrUpdate(user));
        }
        catch (DataIntegrityViolationException ex){
            return "{\"exception\" : \"User already exist.\"}";
        }
        catch (JsonProcessingException ex){
            return "{\"exception\" : \"JSON processing exception.\"}";
        }

    }

    @RequestMapping(value = "/{id}", method = PUT)
    @ResponseBody
    public User saveUser(@RequestBody User user, @PathVariable Integer id) {
        log.info("Save user " + user.getName());
        user.setId(id);
        return service.createOrUpdate(user);
    }

}
