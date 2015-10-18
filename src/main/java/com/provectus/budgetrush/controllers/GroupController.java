package com.provectus.budgetrush.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.provectus.budgetrush.data.Group;
import com.provectus.budgetrush.service.GroupService;

@Slf4j
@Controller
@PreAuthorize("hasRole('ROLE_USER')")
@RequestMapping(value = "/v1/groups", headers = "Accept=application/json")
public class GroupController {

    @Autowired
    private GroupService service;

    @PostFilter("inGroupOrAdmin(filterObject, 'read')")
    @RequestMapping(method = GET)
    @ResponseBody
    public List<Group> listAll() {
        log.info("Get all accounts");
        return service.getAll();
    }

    @PostAuthorize("inGroupOrAdmin(returnObject, 'read')")
    @RequestMapping(value = "/{id}", method = GET)
    @ResponseBody
    public Group getById(@PathVariable Integer id) {
        log.info("Get group by id " + id);
        return service.getById(id);

    }

    @PreAuthorize("inGroupOrAdmin(@groupService.getById(#id), 'delete')")
    @RequestMapping(value = "/{id}", method = DELETE)
    @ResponseBody
    public void delete(@PathVariable Integer id) {
        log.info("Delete group by id " + id);
        service.delete(id);
    }

    @PreAuthorize("inGroupOrAdmin(#user, 'write')")
    @RequestMapping(method = POST)
    @ResponseBody
    public Group newUser(@RequestBody Group group) {
        log.info("Save new group " + group.getName());
        return service.create(group);

    }

    @PreAuthorize("inGroupOrAdmin(#user, 'write')")
    @RequestMapping(value = "/{id}", method = PUT)
    @ResponseBody
    public Group saveUser(@RequestBody Group group, @PathVariable Integer id) {
        log.info("Save group " + group.getName());
        return service.update(group, id);
    }

}
