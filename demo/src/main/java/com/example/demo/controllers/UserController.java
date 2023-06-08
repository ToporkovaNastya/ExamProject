package com.example.demo.controllers;

import com.example.demo.models.*;
import com.example.demo.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
    @Autowired
    AppointmentService ap;
    @Autowired
    MasterService ms;
    @Autowired
    MessageService mess;

    @GetMapping("/userSer")
    public String userSer (Model model,Date data,String id_master, String id_service,String id_time,String hullNumber)
    {
        var us = user.getLoginUser();
        Appointment res = new Appointment();
        if(data!=null&&id_master!=null&&id_service!=null&&id_time!=null&&hullNumber!=null)
        {
            res.setId_service(Integer.parseInt(id_service));
            res.setId_master(Integer.parseInt(id_master));
            res.setId_user(us.getId());
            res.setId_time(Integer.parseInt(id_time));
            res.setDate(data);
            res.setHallNumber(Integer.parseInt(hullNumber));
            res.setStAgr("Не согласовано");
            res.setStDone("Не исполнено");
        }
        ap.addAppointment(res);
        ap.addUserHistory(res);
        return "redirect:/user";
    }
    @PostMapping("/userSer")
    public String direction ()
    {
        return "redirect:/user";
    }
    @GetMapping("/user")
    public ModelAndView user (ModelMap model)
    {
        if(user.signIn())
        {
            model.addAttribute("error2","");
            if(user.signInRole()==1)
            {
                var us = user.getLoginUser();
                model.addAttribute("user",us);
                var aps = ap.getApps(us.getId());
                List<AppointmentUI2> lst = new ArrayList<>();
                for(var a:aps)
                {
                    var apUi = new AppointmentUI2();
                    apUi.setId(a.getId());
                    var s = ap.getProgram(a.getId_service());
                    apUi.setServiceName(s.getName());
                    apUi.setProgram(s.getProgram());
                    apUi.setLevel(s.getLevel());
                    var m = ap.getMaster(a.getId_master());
                    apUi.setMasterSurname(m.getSurname());
                    apUi.setMasterName(m.getName());
                    apUi.setMasterPatronymic(m.getPatronymic());
                    apUi.setPrice(s.getPrice());
                    apUi.setDate(a.getDate());
                    var t = ap.getTime(a.getId_time());
                    apUi.setTimeValue(t.getValue());
                    apUi.setHallNumber(a.getHallNumber());
                    apUi.setStAgr(a.getStAgr());
                    lst.add(apUi);
                }
                model.addAttribute("appointments",lst);
                if(user.signIn())
                {
                    model.addAttribute("loginIn","true");
                }else
                {
                    model.addAttribute("loginIn","false");
                }
                return new ModelAndView("userPages/user",model);
            }else
            {
                model.addAttribute("error2","deny");
                return new ModelAndView("redirect:/accessError",model);
            }
        }
        else
        {
            model.addAttribute("error2","notLog");
            return new ModelAndView("redirect:/accessError",model);
        }
    }
    @GetMapping("/signOutUs")
    public String signOut()
    {
        user.signOut();
        return "redirect:/greeting";
    }
    @GetMapping("/appointment")
    public String app(Model model, String id_service, Date data,String id_time)
    {
        var uit  = new AppointmentUI();
        List<ServiceUI> lst = new ArrayList<>();
        var us = user.getLoginUser();
        model.addAttribute("user",us);
        var s = ap.getServices();
        for (var serv:s)
        {
            var ui = new ServiceUI(serv.getId(),serv.getName(),serv.getProgram(),
                    serv.getLevel(),serv.getId_master(),serv.getDescription(),serv.getPrice());
            var mui = ms.getFio(serv.getId_master());
            ui.setSurname(mui.getSurname());
            ui.setMasterName(mui.getName());
            ui.setPatronymic(mui.getPatronymic());
            lst.add(ui);
        }
        System.out.println(id_service);
        if(id_service!=null)
        {
            int id = Integer.parseInt(id_service);
            var prog = ap.getProgram(id);

            uit.setProgram(prog.getProgram());
            uit.setLevel(prog.getLevel());
            uit.setMaster_id(prog.getId_master());
            uit.setId(id);
            var master = ap.getMaster(prog.getId_master());
            uit.setMasterName(master.getName());
        }
        if(data!=null)
        {
            uit.setDate(data);
        }
        if(id_time!=null)
        {
            int time = Integer.parseInt(id_time);
            uit.setId_time(time);
        }
        model.addAttribute("services",lst);
        model.addAttribute("appointmentUI",uit);
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
    public String time(Model model,Date data,String id_master, String id_service) throws ParseException {
        var us = user.getLoginUser();
        model.addAttribute("user",us);
        if(user.signIn())
        {
            model.addAttribute("loginIn","true");
        }else
        {
            model.addAttribute("loginIn","false");
        }

        //model.addAttribute("hours",allHours);
        //java.sql.Date sqlDate1 =new java.sql.Date(2023-1900,05-01,01);
        if(id_master!=null&&id_service!=null)
        {
            int master_id = Integer.parseInt(id_master);
            int service_id = Integer.parseInt(id_service);
            var allHours = time.getHours(master_id,data,service_id);
            model.addAttribute("data",data);
            if(allHours.size()==0)
            {
                model.addAttribute("workIn","false");
            } else
            {
                model.addAttribute("workIn","true");
            }
            var dHours = time.avaliableHours(master_id,data,service_id);
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
        }else
        {
            model.addAttribute("works");
        }
        //model.addAttribute("dHours",dHours);
         return "userPages/time";
    }
    @GetMapping("/message")
    public String mess (Model model)
    {
        var us = user.getLoginUser();
        model.addAttribute("user",us);
        List<MessageUI>lst = new ArrayList<>();
        var m = mess.getMessage(us.getId());
        for (var message:m)
        {
            var tmp = new MessageUI();
            tmp.setId(message.getId());
            tmp.setApp_id(message.getApp_id());
            tmp.setUser_id(message.getUser_id());
            tmp.setMessage(message.getMessage());
            var st = ap.getAppUser(message.getApp_id());
            tmp.setStatus(st.getStAgr());
            lst.add(tmp);
        }
        model.addAttribute("messages",lst);
        if(user.signIn())
        {
            model.addAttribute("loginIn","true");
        }else
        {
            model.addAttribute("loginIn","false");
        }
        return "userPages/userMessages";
    }
    @GetMapping("/delMessage")
    public String deleteMessage (Model model, @RequestParam("id")int id)
    {
        var us = user.getLoginUser();
        model.addAttribute("user",us);
        var m = mess.getUserMessage(id);
        mess.deleteMessage(id);
        if(user.signIn())
        {
            model.addAttribute("loginIn","true");
        }else
        {
            model.addAttribute("loginIn","false");
        }
        return "redirect:/message";
    }
    @GetMapping("/preventApp")
    public String preventApp (Model model, @RequestParam("id")int id)
    {
        var us = user.getLoginUser();
        model.addAttribute("user",us);
        var app = ap.getAppUser(id);
        ap.updateStDone3(id);
        ap.updateStDone3User(app.getId_time(),app.getDate(),app.getId_master(),app.getId_service());
        if(user.signIn())
        {
            model.addAttribute("loginIn","true");
        }else
        {
            model.addAttribute("loginIn","false");
        }
        return "redirect:/user";
    }
    @GetMapping("/history")
    public String history (Model model)
    {
        var us = user.getLoginUser();
        model.addAttribute("user",us);
        var aps = ap.getHistory(us.getId());
        List<AppointmentUI2> lst = new ArrayList<>();
        for(var a:aps)
        {
            var apUi = new AppointmentUI2();
            apUi.setId(a.getId());
            var s = ap.getProgram(a.getId_service());
            apUi.setServiceName(s.getName());
            apUi.setProgram(s.getProgram());
            apUi.setLevel(s.getLevel());
            var m = ap.getMaster(a.getId_master());
            apUi.setMasterSurname(m.getSurname());
            apUi.setMasterName(m.getName());
            apUi.setMasterPatronymic(m.getPatronymic());
            apUi.setPrice(s.getPrice());
            apUi.setDate(a.getDate());
            var t = ap.getTime(a.getId_time());
            apUi.setTimeValue(t.getValue());
            apUi.setHallNumber(a.getHallNumber());
            apUi.setStAgr(a.getStDone());
            lst.add(apUi);
        }
        model.addAttribute("appointments",lst);
        if(user.signIn())
        {
            model.addAttribute("loginIn","true");
        }else
        {
            model.addAttribute("loginIn","false");
        }
        return "userPages/userHistory";
    }
    @GetMapping("/deleteHistory")
    public String deleteHistory (Model model, @RequestParam("id")int id) {
        var us = user.getLoginUser();
        model.addAttribute("user", us);
        ap.deleteHistory(id);
        if (user.signIn()) {
            model.addAttribute("loginIn", "true");
        } else {
            model.addAttribute("loginIn", "false");
        }
        return "redirect:/history";
    }
}
