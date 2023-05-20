package com.example.demo.services;

import com.example.demo.configs.SqlServerJdbcConfig;
import com.example.demo.mapping.UserAccountMapper;
import com.example.demo.mapping.UserMapper;
import com.example.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.sql.SQLException;
import java.util.List;

@Component
public class UserService {
    @Autowired
    private SqlServerJdbcConfig connection;

    public void getCount(){
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        var count = jdbc.queryForObject("SELECT COUNT(*) FROM [User_desc];", Integer.class);
        System.out.println(count);
    }
    public void getUser(int id){
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        var user = jdbc.queryForObject("SELECT * FROM [User_desc] WHERE [User_desc].[id] = "+id+";",  new UserMapper());
        System.out.println(user.getName());
    }
    public int addUser(User user)
    {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        return jdbc.update(
                "INSERT INTO [dbo].[User_desc]" +
                "           ([Фамилия]" +
                "           ,[Имя]" +
                "           ,[Отчество]" +
                "           ,[Пол]" +
                "           ,[Дата_рождения]" +
                "           ,[Телефон]" +
                "           ,[E-mail])" +
                "     VALUES" +
                "           ('"+user.getSurname()+"'"+
                "           ,'"+user.getName()+"'"+
                "           ,'"+user.getPatronymic()+"'"+
                "           ,'"+user.getGender()+"'"+
                "           ,'"+user.getDate()+"'"+
                "           ,'"+user.getTelephone()+"'"+
                "           ,'"+user.getEmail()+"')");

    }
    public List<User> getUsersForAccount()
    {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        return jdbc.query("Select * FROM [dbo].[User_desc]",new UserAccountMapper());
    }
}