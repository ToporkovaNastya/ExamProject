package com.example.demo.controllers;

import com.example.demo.models.Master;
import com.example.demo.models.Registration;
import com.example.demo.models.User;
import com.example.demo.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLException;

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
        var ms= service.getMasters();
        model.addAttribute("masters",ms);
        return "admin";
    }
    @GetMapping("/masters")
    public String getAllMasters(Model model)
    {
        var ms= service.getMasters();
        model.addAttribute("masters",ms);
        return "masterMain";
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
    @PostMapping("/admin")
    public String addMaster(Model model,@ModelAttribute Master master) throws SQLException
    {
        var ad = service.getLoginAdmin();
        model.addAttribute("admin",ad);
        service.addMaster(master);
        return "admin";
    }
    @GetMapping("/admin/delete")
    public  String delMaster()
    {
        service.delete(1);
        return  "masterMain";
    }
}
