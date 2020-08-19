package com.shr.users.controllers;

import com.shr.users.models.User;
import com.shr.users.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author shruti.mishra
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("hello-world")
    public String helloWorld() {
        return "Hello World";
    }


    @GetMapping("")
    public List<User> findAllUsers() {
        LOG.info("Getting all users.");
        return userService.findAllUsers();
    }
    @GetMapping(value = "/password/{password}")
    public List<User> findByPassword(@PathVariable("password") String password) {
        LOG.info("Getting user by password (i.e encrypted :.");
        return userService.findAllByPassword(password);
    }
    @GetMapping(value = "/userName/{userName}")
    public User findByUserName(@PathVariable("userName") String userName) {
        LOG.info("Getting user by userName:.");
        return userService.findByUserName(userName);
    }

    @PostMapping("/sign-up")
    public User addNewUser(@RequestBody User user) {
        LOG.info("Saving user." + user.getPassword());
        return userService.addNewUser(user);
    }
}