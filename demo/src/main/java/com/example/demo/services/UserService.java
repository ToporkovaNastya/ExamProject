package com.example.demo.services;

import com.example.demo.configs.SqlServerJdbcConfig;
import com.example.demo.mapping.AdminMapper;
import com.example.demo.mapping.UserAccountMapper;
import com.example.demo.mapping.UserMapper;
import com.example.demo.models.Admin;
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
    public void delete(int id)
    {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        jdbc.execute("DELETE FROM [dbo].[User_desc] WHERE [User_desc].[id] = "+id+";");
    }

    public boolean signIn ()
    {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        var f = jdbc.queryForObject("SELECT COUNT ([login_in]) FROM [User] WHERE [User].[login_in] =1;",Integer.class);
        return f>0;
    }
    public boolean signInUser ()
    {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        var f = jdbc.queryForObject("SELECT COUNT ([login_in]) FROM [User] WHERE [User].[login_in] =1 AND [User].[id_role] = 1;",Integer.class);
        return f>0;
    }
    public Integer signInRole ()
    {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        return jdbc.queryForObject("SELECT [id_role] FROM [User] WHERE [User].[login_in] =  1;",Integer.class);
    }

    public User getLoginUser()
    {
        String email = signInEmail();
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        return jdbc.queryForObject("SELECT * FROM [User_desc] WHERE [User_desc].[E-mail] =  '"+email+"'",  new UserMapper());
    }
    public void signIn (String email)
    {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        Integer id = signInId(email);
        jdbc.execute("UPDATE [User] SET [login_in] = 1 WHERE [User].[login] = '"+email+"' AND [User].[id_role] = 1 AND [User].[id] = '"+id+"'; ");
    }
    public void signOut ()
    {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        Integer id = signInId();
        jdbc.execute("UPDATE [User] SET [login_in] = 0 WHERE [User].[id_role] = 1 AND [User].[id] = '"+id+"'; ");
    }
    public String signInEmail ()
    {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        return jdbc.queryForObject("SELECT [login] FROM [User] WHERE [User].[login_in] =  1 AND [User].[id_role] = 1;",String.class);
    }
    public int signInId (String email)
    {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        return jdbc.queryForObject("SELECT [id] FROM [User] WHERE [User].[login] = '"+email+"'AND [User].[id_role] = 1;",Integer.class);
    }
    public int signInId ()
    {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        return jdbc.queryForObject("SELECT [id] FROM [User] WHERE [User].[login_in] = 1 AND [User].[id_role] = 1;",Integer.class);
    }
}