package com.example.demo.services;

import com.example.demo.configs.SqlServerJdbcConfig;
import com.example.demo.mapping.AdminMapper;
import com.example.demo.mapping.MasterMapper;
import com.example.demo.mapping.UserAccountMapper;
import com.example.demo.models.Admin;
import com.example.demo.models.Master;
import com.example.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdminService
{
    @Autowired
    private SqlServerJdbcConfig connection;
    public String getAdmin(String email)
    {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        var admin = jdbc.queryForObject("SELECT * FROM [User] WHERE [User].[login] = '"+email+"' AND [User].[id_role] = 1;",  new AdminMapper());
        System.out.println(admin);
        return admin.getEmail().toString();
    }
    public Admin getLoginAdmin()
    {
        String email = signInEmail();
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        return jdbc.queryForObject("SELECT * FROM [Admin_desc] WHERE [Admin_desc].[E-mail] =  '"+email+"'",  new AdminMapper());
    }
    public void signIn (String email)
    {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        Integer id = signInId(email);
        jdbc.execute("UPDATE [User] SET [login_in] = 1 WHERE [User].[login] = '"+email+"' AND [User].[id_role] = 2 AND [User].[id] = '"+id+"'; ");
    }
    public void signOut ()
    {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        Integer id = signInId();
        jdbc.execute("UPDATE [User] SET [login_in] = 0 WHERE [User].[id_role] = 2 AND [User].[id] = '"+id+"'; ");
    }
    public String signInEmail ()
    {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        return jdbc.queryForObject("SELECT [login] FROM [User] WHERE [User].[login_in] =  1 AND [User].[id_role] = 2;",String.class);
    }
    public int signInId (String email)
    {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        return jdbc.queryForObject("SELECT [id] FROM [User] WHERE [User].[login] = '"+email+"'AND [User].[id_role] = 2;",Integer.class);
    }
    public int signInId ()
    {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        return jdbc.queryForObject("SELECT [id] FROM [User] WHERE [User].[login_in] = 1 AND [User].[id_role] = 2;",Integer.class);
    }
    public int addMaster(Master master)
    {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        return jdbc.update(
                "INSERT INTO [dbo].[Master]" +
                        "           ([Фамилия]" +
                        "           ,[Имя]" +
                        "           ,[Отчество]" +
                        "           ,[Дата_рождения]" +
                        "           ,[Пол]" +
                        "           ,[Телефон]" +
                        "           ,[Грейд]" +
                        "           ,[Стаж]" +
                        "           ,[Должность]" +
                        "           ,[Образование]" +
                        "           ,[Почта])" +
                        "     VALUES" +
                        "           ('"+master.getSurname()+"'"+
                        "           ,'"+master.getName()+"'"+
                        "           ,'"+master.getPatronymic()+"'"+
                        "           ,'"+master.getDate()+"'"+
                        "           ,'"+master.getGender()+"'"+
                        "           ,'"+master.getTelephone()+"'"+
                        "           ,'"+master.getGrade()+"'"+
                        "           ,'"+master.getExperience()+"'"+
                        "           ,'"+master.getPost()+"'"+
                        "           ,'"+master.getEducation()+"'"+
                        "           ,'"+master.getEmail()+"')");

    }
    public List<Master> getMasters()
    {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        return jdbc.query("Select * FROM [dbo].[Master]",new MasterMapper());
    }
    public void delete(int id)
    {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        jdbc.execute("DELETE FROM [dbo].[Master] WHERE [Master].[id] = "+id+";");
    }

}
