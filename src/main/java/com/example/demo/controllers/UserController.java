package com.example.demo.controllers;

import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

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
        return "userAccount";
    }*/
    @GetMapping("/user")
    public ModelAndView user (ModelMap model)
    {
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
        }
    }
    @GetMapping("/user/delete")
    public  String delUser()
    {
        user.delete(1);
        return  "userAccount";
    }
}
