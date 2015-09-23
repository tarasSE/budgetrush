package com.provectus.budgetrush.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class PagesController {


    @RequestMapping(value = "/login_or_signup", method = GET)
    public String signUp(Map<String, Object> model) {

        return "login_or_signup";
    }

    @RequestMapping(value = "/content", method = GET)
    public String users(Map<String, Object> model) {

        return "content";
    }




}