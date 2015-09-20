package com.provectus.budgetrush.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class PagesController {


    @RequestMapping(value = "/signup", method = GET)
    public String signUp(Map<String, Object> model) {

        return "signup";
    }

    @RequestMapping(value = "/login", method = GET)
    public String logIn(Map<String, Object> model) {

        return "login";
    }

    @RequestMapping(value = "/users", method = GET)
    public String users(Map<String, Object> model) {

        return "users";
    }

    @RequestMapping(value = "/accounts", method = GET)
    public String accounts(Map<String, Object> model) {

        return "accounts";
    }

    @RequestMapping(value = "/categories", method = GET)
    public String categories(Map<String, Object> model) {

        return "categories";
    }

    @RequestMapping(value = "/contractors", method = GET)
    public String contractors(Map<String, Object> model) {

        return "contractors";
    }

    @RequestMapping(value = "/currencies", method = GET)
    public String currencies(Map<String, Object> model) {

        return "currencies";
    }

    @RequestMapping(value = "/orders", method = GET)
    public String orders(Map<String, Object> model) {

        return "orders";
    }



}