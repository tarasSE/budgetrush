package com.provectus.budgetrush.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import  static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
public class PagesController {
@Autowired
        @RequestMapping(value = "/index", method = GET)
        public String signUp(){

            return "index";
        }


}