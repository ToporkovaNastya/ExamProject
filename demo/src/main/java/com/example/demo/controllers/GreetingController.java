package com.example.demo.controllers;
import com.example.demo.models.Registration;
import com.example.demo.models.User;
import com.example.demo.services.AdminService;
import com.example.demo.services.RegistrationService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;


@Controller
public class GreetingController
{
    @Autowired
    UserService service;
    @Autowired
    RegistrationService serviceReg;
    @Autowired
    AdminService serviceAdmin;
    @PostMapping("/greeting")
    public ModelAndView greeting(ModelMap model, @ModelAttribute Registration reg)throws SQLException
    {
        String viewName="redirect:/greeting3";
        if(serviceReg.getCount(reg.getLogin())==1)
        {
            model.addAttribute("errorLogin","NotLogin");
            return new ModelAndView(viewName,model);
        }
        viewName= "registration/greeting";
        reg.setId_role(1);
        reg.setLogin_In(0);
        serviceReg.addRegistration(reg);
        return new ModelAndView(viewName,model);
    }
    @GetMapping("/greeting")
    public String greetingGet(Model model,String error,String errorPassword)
    {
        if(service.signIn())
        {
            return "redirect:/directionPage";
        }
        var value = error!=null && error.equals("NotFound") ?"Пользователя с таким логином нет":"";
        var value2 = errorPassword!=null && errorPassword.equals("NotMatch") ?"Вы ввели неверный пароль, попробуйте еще":"";
        model.addAttribute("registration", new Registration());
        if(value!=null)  model.addAttribute("error",value);
        if(value2!=null)  model.addAttribute("errorPassword",value2);
        return "registration/greeting";
    }
    @GetMapping("/greeting2")
    public String greeting2(Model model)
    {
        if(service.signIn())
        {
            return "redirect:/directionPage";
        }
        model.addAttribute("user", new User());
        return "registration/greeting2";
    }
    @PostMapping("/greeting3")
    public String greeting3(Model model,@ModelAttribute User user) throws SQLException
    {
        var reg = new Registration();
        model.addAttribute("registration",  reg);
        reg.setLogin(user.getEmail());
        service.addUser(user);
        return "registration/greeting3";
    }
    @GetMapping("/greeting3")
    public String greeting3Get(Model model,String errorLogin)
    {
        if(service.signIn())
        {
            return "redirect:/directionPage";
        }
        model.addAttribute("registration",  new Registration());
        return "registration/greeting3";
    }
}