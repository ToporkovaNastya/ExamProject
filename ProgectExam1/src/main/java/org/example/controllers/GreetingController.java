package org.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class GreetingController
{
    @GetMapping("/greeting")
    public String greeting()
    {
        return "greeting";
    }
    @GetMapping("/greeting2")
    public String greeting2()
    {
        return "greeting2";
    }
    @GetMapping("/greeting3")
    public String greeting3()
    {
        return "greeting3";
    }
}