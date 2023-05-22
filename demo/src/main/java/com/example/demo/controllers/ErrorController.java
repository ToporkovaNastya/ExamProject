package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorController
{
    @GetMapping("/error")
    public String error ( Model model,String error2)
    {
        model.addAttribute("error","");
        if(error2.equals("deny"))
        {
            model.addAttribute("error","У вас не хватает прав");
        }
        return "Errors";
    }
}
