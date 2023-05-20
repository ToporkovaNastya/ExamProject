package com.example.demo.controllers;

import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController
{
    @Autowired
    UserService user;

    @GetMapping("/user")
    public String user (Model model)
    {
        var us= user.getUsersForAccount();
        System.out.println(us.size());
        model.addAttribute("users1",us);
        return "userAccount";
    }

}
