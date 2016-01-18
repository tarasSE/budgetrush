package com.provectus.budgetrush.controllers;

import com.provectus.budgetrush.data.group.Group;
import com.provectus.budgetrush.service.GroupService;
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

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Slf4j
@Controller
@PreAuthorize("hasRole('ROLE_USER')")
@RequestMapping(value = "/v1/groups", headers = "Accept=application/json")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @PostFilter("inGroupOrAdmin(filterObject, 'read')")
    @RequestMapping(method = GET)
    @ResponseBody
    public List<Group> listAll() {
        log.info("Get all accounts");
        return groupService.getAll();
    }

    @PostAuthorize("inGroupOrAdmin(returnObject, 'read')")
    @RequestMapping(value = "/{id}", method = GET)
    @ResponseBody
    public Group getById(@PathVariable Integer id) {
        log.info("Get group by id " + id);
        return groupService.getById(id);

    }

    @PreAuthorize("inGroupOrAdmin(@groupService.getById(#id), 'delete')")
    @RequestMapping(value = "/{id}", method = DELETE)
    @ResponseBody
    public void delete(@PathVariable Integer id) {
        log.info("Delete group by id " + id);
        groupService.delete(id);
    }

    @PreAuthorize("inGroupOrAdmin(#user, 'write')")  // TODO Fix group creating and  permission
    @RequestMapping(method = POST)
    @ResponseBody
    public Group newGroup(@RequestBody Group group) {
        log.info("Save new group " + group.getName());
        return groupService.create(group);

    }

    @PreAuthorize("inGroupOrAdmin(#user, 'write')") // TODO
    @RequestMapping(value = "/{id}", method = PUT)
    @ResponseBody
    public Group saveGroup(@RequestBody Group group, @PathVariable Integer id) {
        log.info("Save group " + group.getName());
        return groupService.update(group, id);
    }

}
