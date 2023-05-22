package com.example.demo.controllers;

import com.example.demo.models.Master;
import com.example.demo.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController
{
    @Autowired
    AdminService service;
    @GetMapping("/admin")
    public String getAdmin(Model model)
    {
        var ad = service.getLoginAdmin();
        model.addAttribute("admin",ad);
        return "admin";
    }
    @GetMapping("/signOut")
    public String signOut()
    {
        service.signOut();
        return "redirect:/greeting";
    }
    @GetMapping("/add")
    public String getAdminAdd(Model model)
    {
        var ad = service.getLoginAdmin();
        model.addAttribute("admin",ad);
        model.addAttribute("master",new Master());
        return "adminAdd";
    }
}
