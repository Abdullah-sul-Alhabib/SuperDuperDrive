package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.status.StatusCodes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The type Result controller.
 */
@Controller
@RequestMapping("/result")
public class ResultController {
    /**
     * Get result string.
     *
     * @return the string
     */
    @GetMapping
    public String getResult(){
        // @// TODO: Handle request parameters and map error codes with their messages
        return "result";
    }
}
