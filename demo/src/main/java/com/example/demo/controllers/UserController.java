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
        model.addAttribute("users",us);
        return "userAccount";
    }
    @GetMapping("/user/delete")
    public  String delUser()
    {
        user.delete(1);
        return  "userAccount";
    }
}
