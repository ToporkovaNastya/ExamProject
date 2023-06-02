package com.example.demo.controllers;

import com.example.demo.models.ServiceUI;
import com.example.demo.models.ServiceUI2;
import com.example.demo.services.AdminService;
import com.example.demo.services.MasterService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController
{
    @Autowired
    UserService user;
    @Autowired
    AdminService service;
    @Autowired
    MasterService ms;
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
        return "mainPages/index";
    }
    @GetMapping("/masters")
    public String getAllMasters(Model model)
    {
        var ms= service.getMasters();
        model.addAttribute("masters",ms);
        if(user.signIn())
        {
            model.addAttribute("loginIn","true");
        }else
        {
            model.addAttribute("loginIn","false");
        }
        return "mainPages/masterMain";
    }
    @GetMapping("/services")
    public String getAllServices(Model model)
    {
        List<ServiceUI2> lst = new ArrayList<>();
        var ser= service.getServices();
        for (var serv:ser)
        {
            var ui = new ServiceUI2(serv.getId(),serv.getName(),serv.getProgram(),
                    serv.getLevel(),serv.getId_master(),serv.getDescription(),serv.getPrice());
            var mui = ms.getFio(serv.getId_master());
            ui.setSurname(mui.getSurname());
            ui.setMasterName(mui.getName());
            ui.setPatronymic(mui.getPatronymic());
            ui.setMasterGrade(mui.getGrade());
            lst.add(ui);
        }
        model.addAttribute("services",lst);
        if(user.signIn())
        {
            model.addAttribute("loginIn","true");
        }else
        {
            model.addAttribute("loginIn","false");
        }
        return "mainPages/serviceMain";
    }
}
