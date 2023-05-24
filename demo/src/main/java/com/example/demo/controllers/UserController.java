package com.example.demo.controllers;

import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.example.demo.models.Record;

@Controller
public class UserController
{
    @Autowired
    UserService user;
    /*
    @GetMapping("/user")
    public String user (Model model)
    {
        var us= user.getUsersForAccount();
        model.addAttribute("users",us);
        return "userPages/userAccount";
    }*/
    @GetMapping("/user")
    public String user (Model model)
    {  /*
        if(user.signIn())
        {
            if(user.signInRole()==1)
            {
                return new ModelAndView("user",model);
            }else
            {
                model.addAttribute("error2","deny");
                return new ModelAndView("redirect:/error",model);
            }

        }
        else
        {
            model.addAttribute("error2","notLog");
            return new ModelAndView("redirect:/error",model);
        }*/
        var us = user.getLoginUser();
        model.addAttribute("user",us);
        Record record= new Record(1,"XYTXT","NKNOI","JOBO U",3,"NIOERNIO",5);
        model.addAttribute("records",record);
        if(user.signIn())
        {
           model.addAttribute("loginIn","true");
        }else
         {
            model.addAttribute("loginIn","false");
         }
            return "userPages/user";
    }
    @GetMapping("/signOutUs")
    public String signOut()
    {
        user.signOut();
        return "redirect:/greeting";
    }
}
