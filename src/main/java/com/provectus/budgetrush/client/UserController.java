package com.provectus.budgetrush.client;

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
@Controller
public class UserController {

    @Autowired
    private UserService service;

    @RequestMapping(value = "/users", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public List<User> listAll() {
        log.info("Send all users");
        return service.getAll();
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public User getById(@PathVariable Integer id) {
        log.info("Send user by id " + id);
        User user = service.getById(id);
        return user;

    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    @ResponseBody
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public User saveUser(@RequestBody User user) {
        log.info("Save user " + user.getName());
        return service.createAndUpdate(user);
    }
}
