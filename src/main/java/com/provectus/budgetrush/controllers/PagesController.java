package com.provectus.budgetrush.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
public class PagesController {

    @RequestMapping(value = "/index", method = GET)
    public String signUp() {

        return "index";
    }

}