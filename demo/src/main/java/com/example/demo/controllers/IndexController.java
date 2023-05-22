package com.example.demo.controllers;

import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController
{
    @Autowired
    UserService user;
    @GetMapping("/")
    public String getIndex (Model model)
    {
       if(user.signIn())
       {
           model.addAttribute("loginIn","true");
       }else
       {
           model.addAttribute("loginIn","false");
       }
        return "index";
    }
}
