package com.example.demo.controllers;

import com.example.demo.models.Master;
import com.example.demo.models.MasterTime;
import com.example.demo.models.Registration;
import com.example.demo.models.WorkingTimeUI;
import com.example.demo.services.MasterService;
import com.example.demo.services.MasterTimeService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MasterController
{
    @Autowired
    MasterService master;
    @Autowired
    MasterTimeService time;
    @GetMapping("/master")
    public String master(Model model)
    {
        var ms = master.getLoginMaster();
        model.addAttribute("master",ms);
        int id = ms.getId();
        var ser= master.getServices(id);
        model.addAttribute("services",ser);
        model.addAttribute("services");
        if(master.signIn())
        {
            model.addAttribute("loginIn","true");
        }else
        {
            model.addAttribute("loginIn","false");
        }
        return "masterPages/master";
    }
    @GetMapping("/masterTime")
    public String time(Model model, @RequestParam("id") int service_id)
    {
        var ms = master.getLoginMaster();
        model.addAttribute("master",ms);
        int master_id = ms.getId();
        var mt = new MasterTime();
        mt.setMaster_id(master_id);
        mt.setService_id(service_id);
        model.addAttribute("masterTime",mt);
        if(master.signIn())
        {
            model.addAttribute("loginIn","true");
        }else
        {
            model.addAttribute("loginIn","false");
        }

        var allHours = time.getHours();
        model.addAttribute("hours",allHours);


        return "masterPages/masterTime";
    }
    @PostMapping("/masterTime")
    public String addTime(Model model,@ModelAttribute MasterTime mt) throws SQLException
    {
        System.out.println(mt);
        time.addTime(mt);
        var ms = master.getLoginMaster();
        model.addAttribute("master",ms);

        if(master.signIn())
        {
            model.addAttribute("loginIn","true");
        }else
        {
            model.addAttribute("loginIn","false");
        }
        return "redirect:/master";
    }
    @GetMapping("/signOutMs")
    public String signOut()
    {
        master.signOut();
        return "redirect:/greeting";
    }
}
