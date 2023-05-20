package com.example.demo.controllers;

import com.example.demo.models.Registration;
import com.example.demo.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLException;

@Controller
public class DirectionController
{
    @Autowired
    RegistrationService serviceReg;
    @GetMapping("/direction")
    public String login (Model model)
    {
        return "greeting";
    }
    @PostMapping("/direction")
    public String login(@ModelAttribute Registration reg)throws SQLException
    {
        var login = serviceReg.findByLogin(reg.getLogin());
        if(login!=null && reg.getPassword().equals(login.getPassword())) return "admin";
        return "greeting";
    }
}
