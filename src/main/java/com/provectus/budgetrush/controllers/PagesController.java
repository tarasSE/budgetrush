package com.provectus.budgetrush.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class PagesController {


    @RequestMapping(value = "/index", method = GET)
    public String signUp(Map<String, Object> model) {

        model.put("hello", "Hello world");
        return "index";
    }


}