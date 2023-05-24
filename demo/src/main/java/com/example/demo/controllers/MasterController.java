package com.example.demo.controllers;

import com.example.demo.services.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MasterController
{
    @Autowired
    MasterService master;
    @GetMapping("/master")
    public String master(Model model)
    {
        var ms = master.getLoginMaster();
        model.addAttribute("master",ms);
        return "masterPages/master";
    }
    @GetMapping("/signOutMs")
    public String signOut()
    {
        master.signOut();
        return "redirect:/greeting";
    }
}
