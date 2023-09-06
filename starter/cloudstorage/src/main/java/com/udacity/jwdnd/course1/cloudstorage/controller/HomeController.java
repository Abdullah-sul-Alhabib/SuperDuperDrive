package com.udacity.jwdnd.course1.cloudstorage.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * controller for {@linkplain /home} page.
 */
@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping
    public String getHome(){
        return "home";
    }
}
