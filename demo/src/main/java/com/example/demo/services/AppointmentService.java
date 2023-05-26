package com.example.demo.services;

import com.example.demo.configs.SqlServerJdbcConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppointmentService
{
    @Autowired
    private SqlServerJdbcConfig connection;

}
