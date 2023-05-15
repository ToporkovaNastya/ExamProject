package ru.toporkova.main.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class GreetingController 
{
        /*@GetMapping("/greeting")
	public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) 
        {
		model.addAttribute("name", name);
		return "greeting";
	}*/
        @RequestMapping("/greeting")
        ModelAndView greeting() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("greeting.html");
        return modelAndView;
    }
}