package ru.store.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ru.store.models.User;
import ru.store.service.ValidateUser;


@RestController
@RequestMapping("login")
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    private ValidateUser validateUser;

    @Autowired
    public LoginController(ValidateUser validateUser) {
        this.validateUser = validateUser;
    }

    @GetMapping()
    public ModelAndView loginpage() {
        return new ModelAndView("login");
    }

    @GetMapping("/adduser")
    public ModelAndView createUser() {
        return new ModelAndView("newuser");
    }

    @PostMapping(value = "/adduser")
    public RedirectView createUser(@RequestParam String login, @RequestParam String password) {
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        validateUser.add(user);
        LOGGER.info("LOG: {} create new user with login", user.getLogin());
        return new RedirectView("/login");
    }
}