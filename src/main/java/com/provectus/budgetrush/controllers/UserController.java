package com.provectus.budgetrush.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.List;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.provectus.budgetrush.data.Group;
import com.provectus.budgetrush.data.User;
import com.provectus.budgetrush.mail.MailSender;
import com.provectus.budgetrush.mail.ResetPasswordEMailBuilder;
import com.provectus.budgetrush.service.UserService;

@Slf4j
@Controller
@PreAuthorize("hasRole('ROLE_USER')")
@RequestMapping(value = "/v1/users", headers = "Accept=application/json")
public class UserController {

    @Autowired
    private UserService service;
    
    @Autowired
    private MailSender mailSender;
    
    @PostFilter("isObjectOwnerOrAdmin(filterObject, 'read')")
    @RequestMapping(method = GET)
    @ResponseBody
    @Transactional
    public List<User> listAll() {
        log.info("Start to send all users");
        return service.getAll();
    }

    @PostAuthorize("isObjectOwnerOrAdmin(returnObject, 'read')")
    @RequestMapping(value = "/{id}", method = GET)
    @ResponseBody
    public User getById(@PathVariable Integer id) {
        log.info("Send user by id " + id);

        return service.getById(id);

    }

    @PostAuthorize("isObjectOwnerOrAdmin(@userService.getById(#id), 'read')")
    @RequestMapping(value = "/{id}/groups", method = GET)
    @ResponseBody
    public Set<Group> getGroupsById(@PathVariable Integer id) {
        log.info("Send users groups by id " + id);

        return service.getUserGroups(id);

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
        return service.create(user);

    }

    @PreAuthorize("isObjectOwnerOrAdmin(#user, 'write') and chageRolePermission(#user)")
    @RequestMapping(value = "/{id}", method = PUT)
    @ResponseBody
    public User saveUser(@RequestBody User user, @PathVariable Integer id) {
        log.info("Save user " + user.getName());
        return service.update(user, id);
    }
    
    @PreAuthorize("isAnonymous()")
    @RequestMapping(value = "/reset_pass", method = GET)
    @ResponseBody
    @Transactional
    public void resetPassword(@RequestParam String email) {
        log.debug("Start to reset password by email " + email);
        User user = service.findByEmail(email);
        String newPass = RandomStringUtils.random(8);
        user.setPassword(newPass);
        service.update(user, user.getId());
        
        String emailText = ResetPasswordEMailBuilder.newInstance().setPassword(newPass).build();
        
        mailSender.sendEmail(email, "Password resset message", emailText);
        
    }
}
