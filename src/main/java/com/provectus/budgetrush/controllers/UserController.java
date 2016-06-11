package com.provectus.budgetrush.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang.RandomStringUtils;
import javax.inject.Inject;
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

import com.provectus.budgetrush.data.user.User;
import com.provectus.budgetrush.mail.MailSender;
import com.provectus.budgetrush.mail.ResetPasswordMessageBuilder;
import com.provectus.budgetrush.service.UserService;

@Slf4j
@Controller
@PreAuthorize("hasRole('ROLE_USER')")
@RequestMapping(value = "/v1/users", headers = "Accept=application/json")
public class UserController {

    @Inject
    private UserService userService;

    @Inject
    private MailSender mailSender;

    @PostFilter("isObjectOwnerOrAdmin(filterObject, 'read')")
    @RequestMapping(method = GET)
    @ResponseBody
    @Transactional
    public List<User> listAll() {
        log.info("Start to send all users");
        return userService.getAll();
    }

    @PostAuthorize("isObjectOwnerOrAdmin(returnObject, 'read')")
    @RequestMapping(value = "/{id}", method = GET)
    @ResponseBody
    public User getById(@PathVariable Integer id) {
        log.info("Send user by id " + id);

        return userService.getById(id);

    }

    @PreAuthorize("adminOnly() or #name == authentication.name")
    @RequestMapping(value = "/role/{name}", method = GET)
    @ResponseBody
    public String getRole(@PathVariable String name) {

        log.info("Send user role by name " + name);
        String role = userService.getRoleByName(name).toString();
        return "{\"role\"" + ":" + "\"" + role + "\"}";

    }

    @PreAuthorize("adminOnly()")
    @RequestMapping(value = "/{id}", method = DELETE)
    @ResponseBody
    public void delete(@PathVariable Integer id) {
        userService.delete(id);
    }

    @PreAuthorize("adminOnly() or isAnonymous()")
    @RequestMapping(method = POST)
    @ResponseBody
    public User newUser(@RequestBody User user) {
        log.info("Save user " + user.getName());
        return userService.create(user);

    }

    @PreAuthorize("isObjectOwnerOrAdmin(#user, 'write') and chageRolePermission(#user)")
    @RequestMapping(value = "/{id}", method = PUT)
    @ResponseBody
    public User saveUser(@RequestBody User user, @PathVariable Integer id) {
        log.info("Save user " + user.getName());
        return userService.update(user, id);
    }

    @PreAuthorize("isAnonymous()")
    @RequestMapping(value = "/reset_pass", method = GET)
    @ResponseBody
    @Transactional
    public void resetPassword(@RequestParam String email, HttpServletResponse response) {
        log.info("Start to reset password by email " + email);
        User user = userService.findByEmail(email);
        String newPass = RandomStringUtils.randomAlphanumeric(8);
        user.setPassword(newPass);
        userService.update(user, user.getId());

        String emailText = ResetPasswordMessageBuilder.newInstance().setPassword(newPass).setName(user.getName()).build();

        mailSender.sendEmail(email, "Password reset message", emailText);

        response.setStatus(HttpServletResponse.SC_OK);
    }
}
