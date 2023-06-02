package com.example.demo.services;

import com.example.demo.configs.SqlServerJdbcConfig;
import com.example.demo.mapping.*;
import com.example.demo.models.AppointmentId;
import com.example.demo.models.Master;
import com.example.demo.models.MasterTime;
import com.example.demo.models.WorkingTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


import java.sql.Date;
import java.util.List;

@Component
public class WorkingTimeService
{
    @Autowired
    private SqlServerJdbcConfig connection;

    public List<MasterTime> getHours(int id_master, Date date, int service_id)
    {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        return jdbc.query("Select * FROM [dbo].[WorkingTime2]  WHERE [WorkingTime2].[id_мастера] = "+id_master+" AND  [WorkingTime2].[Дата] = '"+date+"' AND id_услуги = "+service_id+" ORDER by value;",new MasterTimeMapper());
    }
    public List<AppointmentId> avaliableHours(int id_master, Date date, int service_id)
    {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        return jdbc.query("Select id_времени FROM [dbo].[Appoinment] WHERE [Appoinment].[id_мастера] = "+id_master+" AND  [Appoinment].[Дата] = '"+date+"' AND id_услуги = "+service_id+";",new AppointmentIdMapper());
    }

}
