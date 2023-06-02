package com.example.demo.controllers;

import com.example.demo.models.*;
import com.example.demo.services.AppointmentService;
import com.example.demo.services.MasterService;
import com.example.demo.services.MasterTimeService;
import com.example.demo.services.MessageService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
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
    @Autowired
    AppointmentService ap;
    @Autowired
    MessageService mess;

    @GetMapping("/master")
    public ModelAndView master(ModelMap model, @ModelAttribute MasterTime mas, String error)
    {
        var ms = master.getLoginMaster();
        model.addAttribute("master",ms);
        int id = ms.getId();
        var ser= master.getServices(id);
        model.addAttribute("services",ser);
        model.addAttribute("masterTime",mas);
        var value = error!=null && error.equals("NotTime") ?"Такое время уже занято":"";
        System.out.println(value);
        if(value!=null)  model.addAttribute("error",value);
        if(master.signIn())
        {
            model.addAttribute("loginIn","true");
        }else
        {
            model.addAttribute("loginIn","false");
        }
        return new ModelAndView("masterPages/master",model);
    }
    @PostMapping("/masterTime")
    public String addTime(Model model,@ModelAttribute MasterTime mt) throws SQLException
    {
        var ms = master.getLoginMaster();
        model.addAttribute("master",ms);
        int master_id = ms.getId();
        mt.setMaster_id(master_id);
        System.out.println(mt);
        if(!time.getValues(mt.getMaster_id(),mt.getDate(),mt.getService_id(), mt.getValue()))
        {
            time.addTime(mt);

        }else
        {
            model.addAttribute("error","NotTime");
            return "exceptions/errors.html";
        }
        if(master.signIn())
        {
            model.addAttribute("loginIn","true");
        }else
        {
            model.addAttribute("loginIn","false");
        }
        return "redirect:/master";
    }
    @PostMapping("/masterTime2")
    public String addTime2(Model model,@ModelAttribute MasterTime mt) throws SQLException
    {
        var ms = master.getLoginMaster();
        model.addAttribute("master",ms);
        int master_id = ms.getId();
        var allHours = time.getHours(master_id,mt.getDate(),mt.getService_id());
        var dHours = time.avaliableHours(master_id,mt.getDate(), mt.getService_id());
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

        if(master.signIn())
        {
            model.addAttribute("loginIn","true");
        }else
        {
            model.addAttribute("loginIn","false");
        }
        return "masterPages/masterTime";
    }
    @GetMapping("/signOutMs")
    public String signOut()
    {
        master.signOut();
        return "redirect:/greeting";
    }
    @GetMapping("/masterAp")
    public String masterAp (Model model)
    {
        var ms = master.getLoginMaster();
        model.addAttribute("master",ms);
        var aps = ap.getMasterApps(ms.getId());
        List<AppointmentUI2> lst = new ArrayList<>();
        for(var a:aps)
        {
            var apUi = new AppointmentUI2();
            apUi.setId(a.getId());
            var s = ap.getProgram(a.getId_service());
            apUi.setServiceName(s.getName());
            apUi.setProgram(s.getProgram());
            apUi.setLevel(s.getLevel());
            var u = ap.getUser(a.getId_user());
            apUi.setMasterSurname(u.getSurname());
            apUi.setMasterName(u.getName());
            apUi.setMasterPatronymic(u.getPatronymic());
            apUi.setPrice(s.getPrice());
            apUi.setDate(a.getDate());
            var t = ap.getTime(a.getId_time());
            apUi.setTimeValue(t.getValue());
            apUi.setHallNumber(a.getHallNumber());
            apUi.setStAgr(a.getStAgr());
            lst.add(apUi);
        }

        model.addAttribute("appointments",lst);

        if(master.signIn())
        {
            model.addAttribute("loginIn","true");
        }else
        {
            model.addAttribute("loginIn","false");
        }
        return "masterPages/masterAppointment";
    }
    @GetMapping("/updateApp")
    public String updateStAgr1(Model model,@RequestParam("id") int id)
    {
        var ms = master.getLoginMaster();
        model.addAttribute("master",ms);
        ap.updateStAgr(id);
        var message = new Message();
        message.setApp_id(id);
        var us = ap.getAppUser(id);
        message.setUser_id(us.getId_user());
        ap.updateStAgrUser(us.getId_time(),us.getDate(),us.getId_master(),us.getId_service());
        model.addAttribute("message",message);
        if(master.signIn())
        {
            model.addAttribute("loginIn","true");
        }else
        {
            model.addAttribute("loginIn","false");
        }
        return "masterPages/masterAgree";
    }
    @PostMapping("/updateApp")
    public String mess(Model model,@ModelAttribute Message message)
    {
        var ms = master.getLoginMaster();
        model.addAttribute("master",ms);
        mess.addMessage(message);
        if(master.signIn())
        {
            model.addAttribute("loginIn","true");
        }else
        {
            model.addAttribute("loginIn","false");
        }
        return "redirect:/masterAp";
    }
    @GetMapping("/updateApp2")
    public String updateStAgr2(Model model,@RequestParam("id") int id)
    {
        var ms = master.getLoginMaster();
        model.addAttribute("master",ms);

        var message = new Message();
        message.setApp_id(id);
        var us = ap.getAppUser(id);
        message.setUser_id(us.getId_user());
        model.addAttribute("message",message);

        if(master.signIn())
        {
            model.addAttribute("loginIn","true");
        }else
        {
            model.addAttribute("loginIn","false");
        }
        return "masterPages/masterDisagree";
    }
    @PostMapping("/updateApp2")
    public String message(Model model,@ModelAttribute Message message)
    {
        var ms = master.getLoginMaster();
        model.addAttribute("master",ms);
        mess.addMessage(message);
        ap.updateStDone2(message.getApp_id());
        int id = message.getApp_id();
        var app = ap.getAppUser(id);
        ap.updateStDone2User(app.getId_time(),app.getDate(),app.getId_master(),app.getId_service());
        if(master.signIn())
        {
            model.addAttribute("loginIn","true");
        }else
        {
            model.addAttribute("loginIn","false");
        }
        return "redirect:/masterAp";
    }
    @GetMapping("/updateApp3")
    public String updateStDone(Model model,@RequestParam("id") int id)
    {
        var ms = master.getLoginMaster();
        model.addAttribute("master",ms);
        ap.updateStDone(id);
        var app = ap.getAppUser(id);
        ap.updateStDoneUser(app.getId_time(),app.getDate(),app.getId_master(),app.getId_service());
        if(master.signIn())
        {
            model.addAttribute("loginIn","true");
        }else
        {
            model.addAttribute("loginIn","false");
        }
        return "redirect:/masterAp";
    }
    @GetMapping("/masterHistory")
    public String masterHistory (Model model)
    {
        var ms = master.getLoginMaster();
        model.addAttribute("master",ms);
        var aps = ap.getMasterHistory(ms.getId());
        List<AppointmentUI2> lst = new ArrayList<>();
        for(var a:aps)
        {
            var apUi = new AppointmentUI2();
            apUi.setId(a.getId());
            var s = ap.getProgram(a.getId_service());
            apUi.setServiceName(s.getName());
            apUi.setProgram(s.getProgram());
            apUi.setLevel(s.getLevel());
            var u = ap.getUser(a.getId_user());
            apUi.setMasterSurname(u.getSurname());
            apUi.setMasterName(u.getName());
            apUi.setMasterPatronymic(u.getPatronymic());
            apUi.setPrice(s.getPrice());
            apUi.setDate(a.getDate());
            var t = ap.getTime(a.getId_time());
            apUi.setTimeValue(t.getValue());
            apUi.setHallNumber(a.getHallNumber());
            apUi.setStAgr(a.getStDone());
            lst.add(apUi);
        }

        model.addAttribute("appointments",lst);

        if(master.signIn())
        {
            model.addAttribute("loginIn","true");
        }else
        {
            model.addAttribute("loginIn","false");
        }
        return "masterPages/masterHistory";
    }
    @GetMapping("/delMsHistory")
    public String deleteMasterHistory(Model model,@RequestParam("id") int id)
    {
        var ms = master.getLoginMaster();
        model.addAttribute("master",ms);
        ap.deleteAppointment(id);
        if(master.signIn())
        {
            model.addAttribute("loginIn","true");
        }else
        {
            model.addAttribute("loginIn","false");
        }
        return "redirect:/masterHistory";
    }

}
