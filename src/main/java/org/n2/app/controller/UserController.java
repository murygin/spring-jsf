package org.n2.app.controller;

import org.n2.app.persistence.User;
import org.n2.app.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    IUserService userService;

    @RequestMapping(path = "/users")
    public List<User> users(){
        List<User> users = new ArrayList<>();
        for(User user : userService.listUsers()){
            users.add(user);
        }
        return users;
    }

}
