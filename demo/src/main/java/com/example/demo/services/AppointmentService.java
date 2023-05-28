package com.example.demo.services;

import com.example.demo.configs.SqlServerJdbcConfig;
import com.example.demo.mapping.ServiceMapper;
import com.example.demo.models.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AppointmentService
{
    @Autowired
    private SqlServerJdbcConfig connection;

    public List<Service> getServices(String name)
    {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        return jdbc.query("Select * FROM [dbo].[Service] WHERE [Service].[Название] = '"+name+"';",new ServiceMapper());
    }

}
