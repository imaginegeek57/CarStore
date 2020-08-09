package ru.store.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@RestController
@RequestMapping("/main")
public class DataController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataController.class);

    @GetMapping()
    public ModelAndView mainPage(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("login", principal.getName());
        LOGGER.info("LOG: user {} visit main page", principal.getName());
        return modelAndView;
    }
}