package com.example.demo.controllers;

import com.example.demo.models.Appointment;
import com.example.demo.models.WorkingTimeUI;
import com.example.demo.services.UserService;
import com.example.demo.services.WorkingTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


@Controller
public class UserController
{
    @Autowired
    UserService user;
    @Autowired
    WorkingTimeService time;
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
        //Record record= new Record(1,"XYTXT","NKNOI","JOBO U",3,"NIOERNIO",5);
        //model.addAttribute("records",record);
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
    @GetMapping("/appointment")
    public String app(Model model)
    {
        var us = user.getLoginUser();
        model.addAttribute("user",us);
        model.addAttribute("appointment",new Appointment());
        if(user.signIn())
        {
            model.addAttribute("loginIn","true");
        }else
        {
            model.addAttribute("loginIn","false");
        }
        return "userPages/userAppointment";
    }
    @GetMapping("/time")
    public String time(Model model) throws ParseException {
        var us = user.getLoginUser();
        model.addAttribute("user",us);
        if(user.signIn())
        {
            model.addAttribute("loginIn","true");
        }else
        {
            model.addAttribute("loginIn","false");
        }
        var allHours = time.getHours();
        //model.addAttribute("hours",allHours);
        java.sql.Date sqlDate1 =new java.sql.Date(2023-1900,05-01,27);
        var dHours = time.avaliableHours(12,sqlDate1);
        //model.addAttribute("dHours",dHours);
        List<WorkingTimeUI> lst = new ArrayList<>();
        for(var allhour:allHours)
        {
            var work = new WorkingTimeUI(allhour.getId(),allhour.getValue());
            for(var dHour:dHours)
            {
                if(dHour.getId()==allhour.getId())
                {
                    work.setDisabled(true);
                    break;
                }
            }
            lst.add(work);
        }
         model.addAttribute("works",lst);
         return "userPages/time";
    }
}
