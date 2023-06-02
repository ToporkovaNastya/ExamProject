package com.example.demo.services;

import com.example.demo.configs.SqlServerJdbcConfig;
import com.example.demo.mapping.AppointmentIdMapper;
import com.example.demo.mapping.MasterTimeMapper;
import com.example.demo.mapping.WorkingTimeMapper;
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
public class MasterTimeService
{
    @Autowired
    private SqlServerJdbcConfig connection;
    public int addTime(MasterTime masterTime)
    {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        return jdbc.update(
                "INSERT INTO [dbo].[WorkingTime2]" +
                        "           ([id_мастера]" +
                        "           ,[id_услуги]" +
                        "           ,[Дата]" +
                        "           ,[value])" +
                        "     VALUES" +
                        "           ('"+masterTime.getMaster_id()+"'"+
                        "           ,'"+masterTime.getService_id()+"'"+
                        "           ,'"+masterTime.getDate()+"'"+
                        "           ,'"+masterTime.getValue()+"')");
    }
    public List<MasterTime> getHours(int master_id,Date date, int service_id)
    {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        return jdbc.query("Select * FROM [dbo].[WorkingTime2] WHERE id_мастера = "+master_id+" AND Дата = '"+date+"' AND id_услуги = "+service_id+" ORDER BY value;",new MasterTimeMapper());
    }
    public Boolean getValues(int master_id,Date date, int service_id,String value)
    {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        var f = jdbc.queryForObject("Select COUNT(*) FROM [dbo].[WorkingTime2] WHERE id_мастера = "+master_id+" AND Дата = '"+date+"' AND id_услуги = "+service_id+" AND value = '"+value+"' ;", Integer.class);
        return f>0;
    }
    public List<AppointmentId> avaliableHours(int id_master, Date date, int service_id)
    {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        return jdbc.query("Select id_времени FROM [dbo].[Appoinment] WHERE [Appoinment].[id_мастера] = "+id_master+" AND  [Appoinment].[Дата] = '"+date+"' AND id_услуги = "+service_id+";",new AppointmentIdMapper());
    }
}
