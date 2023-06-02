package com.example.demo.controllers;

import com.example.demo.models.Master;
import com.example.demo.models.Registration;
import com.example.demo.models.Service;
import com.example.demo.models.ServiceUI;
import com.example.demo.services.AdminService;
import com.example.demo.services.MasterService;
import com.example.demo.services.RegistrationService;
import com.example.demo.services.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController
{
    @Autowired
    AdminService service;
    @Autowired
    UserService user;
    @Autowired
    MasterService ms;
    @Autowired
    RegistrationService regSer;
    @GetMapping("/admin")
    public String getAdmin(Model model)
    {
        var ad = service.getLoginAdmin();
        model.addAttribute("admin",ad);
        var ms= service.getMasters();
        model.addAttribute("masters",ms);
        if(user.signIn())
        {
            model.addAttribute("loginIn","true");
        }else
        {
            model.addAttribute("loginIn","false");
        }
        return "administrator/admin";
    }
    @GetMapping("/service")
    public String getService(Model model,@ModelAttribute Service sr)
    {
        List<ServiceUI> lst = new ArrayList<>();
        var ad = service.getLoginAdmin();
        model.addAttribute("admin",ad);
        var ser= service.getServices();
        for (var serv:ser)
        {
            var ui = new ServiceUI(serv.getId(),serv.getName(),serv.getProgram(),
                    serv.getLevel(),serv.getId_master(),serv.getDescription(),serv.getPrice());
            var mui = ms.getFio(serv.getId_master());
            ui.setSurname(mui.getSurname());
            ui.setMasterName(mui.getName());
            ui.setPatronymic(mui.getPatronymic());
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
        return "administrator/service";
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
        if(user.signIn())
        {
            model.addAttribute("loginIn","true");
        }else
        {
            model.addAttribute("loginIn","false");
        }
        return "administrator/adminAdd";
    }
    @PostMapping("/admin")
    public String addMaster(Model model,@ModelAttribute Master master) throws SQLException
    {
        service.addMaster(master);
        Registration registration = new Registration();
        registration.setLogin(master.getEmail());
        registration.setPassword(RandomStringUtils.randomNumeric(8));
        registration.setId_role(3);
        registration.setLogin_In(0);
        regSer.addRegistration(registration);
        var ad = service.getLoginAdmin();
        model.addAttribute("admin",ad);
        var ms= service.getMasters();
        model.addAttribute("masters",ms);
        if(user.signIn())
        {
            model.addAttribute("loginIn","true");
        }else
        {
            model.addAttribute("loginIn","false");
        }
        return "administrator/admin";
    }
    @GetMapping("/delete")
    public String getDelete(Model model, int id,String email)
    {
        service.deleteUser(email);
        service.deleteAppointment(id);
        service.deleteTime(id);
        service.deletemasterSer(id);
        service.delete(id);
        var ad = service.getLoginAdmin();
        model.addAttribute("admin",ad);
        var ms= service.getMasters();
        model.addAttribute("masters",ms);
        if(user.signIn())
        {
            model.addAttribute("loginIn","true");
        }else
        {
            model.addAttribute("loginIn","false");
        }
        return "administrator/admin";
    }
    @GetMapping("/update")
    public String getUpdate(Model model, int id)
    {
        var ad = service.getLoginAdmin();
        model.addAttribute("admin",ad);
        var  ms = service.getMaster(id);
        model.addAttribute("master",ms);
        if(user.signIn())
        {
            model.addAttribute("loginIn","true");
        }else
        {
            model.addAttribute("loginIn","false");
        }
        return "administrator/adminUpdate";
    }
    @PostMapping("/update")
    public String update(Model model, @ModelAttribute Master master)
    {
        var ad = service.getLoginAdmin();
        model.addAttribute("admin",ad);
        var  ms = service.getMaster(master.getId());
        service.update(master);
        service.updateUser(master.getEmail(),ms.getEmail());
        if(user.signIn())
        {
            model.addAttribute("loginIn","true");
        }else
        {
            model.addAttribute("loginIn","false");
        }
        return "redirect:/admin";
    }
    @GetMapping("/deleteSer")
    public String deleteService(Model model, int id)
    {
        service.deleteSer(id);
        var ad = service.getLoginAdmin();
        model.addAttribute("admin",ad);
        var ser= service.getServices();
        model.addAttribute("services",ser);
        if(user.signIn())
        {
            model.addAttribute("loginIn","true");
        }else
        {
            model.addAttribute("loginIn","false");
        }
        return "redirect:/service";
    }
    @GetMapping("/addSer")
    public String getServiceAdd(Model model)
    {
        var ad = service.getLoginAdmin();
        model.addAttribute("admin",ad);
        model.addAttribute("service",new Service());
        var ms= service.getMasters();
        model.addAttribute("masters",ms);
        if(user.signIn())
        {
            model.addAttribute("loginIn","true");
        }else
        {
            model.addAttribute("loginIn","false");
        }
        return "administrator/addService";
    }

    @PostMapping("/service")
    public String addSer(Model model,@ModelAttribute Service ser) throws SQLException
    {
        service.addService(ser);
        var ad = service.getLoginAdmin();
        model.addAttribute("admin",ad);
        var serv = service.getServices();
        model.addAttribute("services",serv);
        if(user.signIn())
        {
            model.addAttribute("loginIn","true");
        }else
        {
            model.addAttribute("loginIn","false");
        }
        return "redirect:/service";
    }
    @GetMapping("/updateSer")
    public String updateSer(Model model, int id)
    {
        var ad = service.getLoginAdmin();
        model.addAttribute("admin",ad);
        var  s = service.getService(id);
        model.addAttribute("service",s);
        var ms= service.getMasters();
        model.addAttribute("masters",ms);
        if(user.signIn())
        {
            model.addAttribute("loginIn","true");
        }else
        {
            model.addAttribute("loginIn","false");
        }
        return "administrator/updateSer";
    }
    @PostMapping("/updateSer")
    public String upd(Model model, @ModelAttribute Service serv)
    {
        var ad = service.getLoginAdmin();
        model.addAttribute("admin",ad);
        var  ms = service.getService(serv.getId());
        service.updateSer(serv);
        if(user.signIn())
        {
            model.addAttribute("loginIn","true");
        }else
        {
            model.addAttribute("loginIn","false");
        }
        return "redirect:/service";
    }
}
