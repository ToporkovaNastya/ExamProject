package com.example.demo.controllers;

import com.example.demo.models.Registration;
import com.example.demo.services.AdminService;
import com.example.demo.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

@Controller
public class DirectionController
{
    @Autowired
    RegistrationService serviceReg;
    @Autowired
    AdminService serviceAdmin;

    @PostMapping("/direction")
    public ModelAndView login(ModelMap model,@ModelAttribute Registration reg)throws SQLException
    {
        String viewName="redirect:/greeting";
        if(serviceReg.getCount(reg.getLogin())==1)
        {
            var login = serviceReg.findByLogin(reg.getLogin());
            if(login!=null && reg.getPassword().equals(login.getPassword())&&login.getId_role()==1)
            {
                viewName="redirect:/user";
                return new ModelAndView(viewName,model);
            }
            if(login!=null && reg.getPassword().equals(login.getPassword())&&login.getId_role()==2)
            {
                viewName="redirect:/admin";
                serviceAdmin.signIn(login.getLogin());
                return new ModelAndView(viewName,model);
            }
            if(login!=null && reg.getPassword().equals(login.getPassword())&&login.getId_role()==3)
            {
                viewName="redirect:/master";
                return new ModelAndView(viewName,model);
            }
            if(!reg.getPassword().equals(login.getPassword()))
            {
                viewName ="redirect:/greeting";
                model.addAttribute("errorPassword","NotMatch");
            }
        } else model.addAttribute("error","NotFound");

        return new ModelAndView(viewName,model);
    }
}
