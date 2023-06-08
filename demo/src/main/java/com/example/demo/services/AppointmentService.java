package com.example.demo.services;

import com.example.demo.configs.SqlServerJdbcConfig;
import com.example.demo.mapping.*;
import com.example.demo.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Date;
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
    public List<Service> getServices()
    {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        return jdbc.query("Select * FROM [dbo].[Service];",new ServiceMapper());
    }
    public Service getProgram(int id)
    {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        return jdbc.queryForObject("Select * FROM [dbo].[Service] WHERE [Service].[id] = "+id+";",new ServiceMapper());
    }
    public Master getMaster(int id)
    {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        return jdbc.queryForObject("Select * FROM [dbo].[Master] WHERE [Master].[id] = "+id+";",new MasterMapper());
    }
    public User getUser(int id)
    {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        return jdbc.queryForObject("Select * FROM [dbo].[User_desc] WHERE [User_desc].[id] = "+id+";",new UserMapper());
    }
    public Appointment getAppUser(int id)
    {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        return jdbc.queryForObject("Select * FROM [dbo].[Appoinment] WHERE [Appoinment].[id] = "+id+";",new AppointmentMapper());
    }
    public MasterTime getTime(int id)
    {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        return jdbc.queryForObject("Select * FROM [dbo].[WorkingTime2] WHERE [WorkingTime2].[id] = "+id+";",new MasterTimeMapper());
    }
    public int addAppointment(Appointment ap)
    {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        return jdbc.update(
                "INSERT INTO [dbo].[Appoinment]" +
                        "           ([id_услуги]" +
                        "           ,[id_мастера]" +
                        "           ,[Дата]" +
                        "           ,[id_времени]" +
                        "           ,[id_пользователя]" +
                        "           ,[Номер_зала]" +
                        "           ,[Статус_исполнения]" +
                        "           ,[Статус_согласования])" +
                        "     VALUES" +
                        "           ('"+ap.getId_service()+"'"+
                        "           ,'"+ap.getId_master()+"'"+
                        "           ,'"+ap.getDate()+"'"+
                        "           ,'"+ap.getId_time()+"'"+
                        "           ,'"+ap.getId_user()+"'"+
                        "           ,'"+ap.getHallNumber()+"'"+
                        "           ,'"+ap.getStDone()+"'"+
                        "           ,'"+ap.getStAgr()+"')");

    }
    public int addUserHistory(Appointment ap)
    {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        return jdbc.update(
                "INSERT INTO [dbo].[User_History]" +
                        "           ([id_услуги]" +
                        "           ,[id_мастера]" +
                        "           ,[Дата]" +
                        "           ,[id_времени]" +
                        "           ,[id_пользователя]" +
                        "           ,[Номер_зала]" +
                        "           ,[Статус_исполнения]" +
                        "           ,[Статус_согласования])" +
                        "     VALUES" +
                        "           ('"+ap.getId_service()+"'"+
                        "           ,'"+ap.getId_master()+"'"+
                        "           ,'"+ap.getDate()+"'"+
                        "           ,'"+ap.getId_time()+"'"+
                        "           ,'"+ap.getId_user()+"'"+
                        "           ,'"+ap.getHallNumber()+"'"+
                        "           ,'"+ap.getStDone()+"'"+
                        "           ,'"+ap.getStAgr()+"')");

    }
    public List<Appointment> getApps(int id)
    {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        return jdbc.query("Select * FROM [dbo].[Appoinment] WHERE [Appoinment].[id_пользователя] = "+id+"  AND [Статус_исполнения]='Не исполнено';",new AppointmentMapper());
    }
    public List<Appointment> getHistory(int id)
    {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        return jdbc.query("Select * FROM [dbo].[User_History] WHERE [User_History].[id_пользователя] = "+id+"  AND ( [Статус_исполнения]='Исполнено' OR [Статус_исполнения]='Отказано в исполнении' OR [Статус_исполнения]='Отменено пользователем') ;",new AppointmentMapper());
    }
    public List<Appointment> getMasterApps(int id)
    {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        return jdbc.query("Select * FROM [dbo].[Appoinment] WHERE [Appoinment].[id_мастера] = "+id+"  AND ([Статус_исполнения]='Не исполнено' OR [Статус_исполнения]='Отменено пользователем');",new AppointmentMapper());
    }
    public List<Appointment> getMasterHistory(int id)
    {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        return jdbc.query("Select * FROM [dbo].[Appoinment] WHERE [Appoinment].[id_мастера] = "+id+"  AND ([Статус_исполнения]='Исполнено' OR  [Статус_исполнения]='Отказано в исполнении' OR [Статус_исполнения]='Отменено пользователем');",new AppointmentMapper());
    }
    public void updateStAgr (int id)
    {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        jdbc.execute("UPDATE [Appoinment] SET [Статус_согласования] = 'Согласовано' WHERE [Appoinment].[id] = "+id+"; ");
    }
    public void updateStAgrUser (int id_time, Date date,int id_master,int id_service)
    {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        jdbc.execute("UPDATE [User_History] SET [Статус_согласования] = 'Согласовано' WHERE id_времени = "+id_time+" AND Дата= '"+date+"' AND id_мастера = "+id_master+" AND id_услуги = "+id_service+";");
    }
    public void updateStDone (int id)
    {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        jdbc.execute("UPDATE [Appoinment] SET [Статус_исполнения] = 'Исполнено' WHERE [Appoinment].[id] = "+id+"; ");
    }
    public void updateStDoneUser (int id_time, Date date,int id_master,int id_service)
    {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        jdbc.execute("UPDATE [User_History] SET [Статус_исполнения] = 'Исполнено' WHERE id_времени = "+id_time+" AND Дата= '"+date+"' AND id_мастера = "+id_master+" AND id_услуги = "+id_service+";");
    }
    public void updateStDone2(int id)
    {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        jdbc.execute("UPDATE [Appoinment] SET [Статус_исполнения] = 'Отказано в исполнении' WHERE [Appoinment].[id] = "+id+"; ");
    }
    public void updateStDone3(int id)
    {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        jdbc.execute("UPDATE [Appoinment] SET [Статус_исполнения] = 'Отменено пользователем' WHERE [Appoinment].[id] = "+id+"; ");
    }
    public void updateStDone2User(int id_time, Date date,int id_master,int id_service)
    {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        jdbc.execute("UPDATE [User_History] SET [Статус_исполнения] = 'Отказано в исполнении' WHERE id_времени = "+id_time+" AND Дата= '"+date+"' AND id_мастера = "+id_master+" AND id_услуги = "+id_service+";");
    }
    public void updateStDone3User(int id_time, Date date,int id_master,int id_service)
    {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        jdbc.execute("UPDATE [User_History] SET [Статус_исполнения] = 'Отменено пользователем' WHERE id_времени = "+id_time+" AND Дата= '"+date+"' AND id_мастера = "+id_master+" AND id_услуги = "+id_service+";");
    }
    public void deleteAppointment(int id)
    {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        jdbc.execute("DELETE FROM [dbo].[Appoinment] WHERE id = "+id+";");
    }
    public void deleteHistory(int id)
    {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        jdbc.execute("DELETE FROM [dbo].[User_History] WHERE id = "+id+";");
    }
}
