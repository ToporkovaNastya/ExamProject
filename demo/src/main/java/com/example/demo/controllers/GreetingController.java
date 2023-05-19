package com.example.demo.controllers;
import com.example.demo.configs.SqlServerJdbcConfig;
import com.example.demo.models.User;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.SQLException;
import java.util.Date;

@Controller
public class GreetingController
{
    @Autowired
    UserService service;
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
    public String greeting3() throws SQLException
    {
        User test = new User("Lokuta","Olga","Olegovna",
                "Женский","29-09-2003","+7-917-982-46-05","olg@mailru");
        service.getCount();
        service.getUser(2);
        service.addUser(test);
        return "greeting3";
    }
}