package com.example.demo.services;

import com.example.demo.configs.SqlServerJdbcConfig;
import com.example.demo.mapping.AdminMapper;
import com.example.demo.mapping.MasterMapper;
import com.example.demo.mapping.ServiceMapper;
import com.example.demo.mapping.UserAccountMapper;
import com.example.demo.models.Admin;
import com.example.demo.models.Master;
import com.example.demo.models.Service;
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
                        "           ,[Описание]" +
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
                        "           ,'"+master.getDesc()+"'"+
                        "           ,'"+master.getEmail()+"')");

    }
    public List<Master> getMasters()
    {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        return jdbc.query("Select * FROM [dbo].[Master]",new MasterMapper());
    }
    public List<Service> getServices()
    {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        return jdbc.query("Select * FROM [dbo].[Service]",new ServiceMapper());
    }
    public void delete(int id)
    {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        jdbc.execute("DELETE FROM [dbo].[Master] WHERE [Master].[id] = '"+id+"';");
    }
    public void update(Master master)
    {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        jdbc.execute("UPDATE Master SET [Фамилия]='"+ master.getSurname()+
                "', Имя='" + master.getName()+
                "', Отчество='" + master.getSurname()+
                "', Дата_рождения='" + master.getDate()+
                "', Пол='" + master.getGender()+
                "', Телефон='" + master.getTelephone()+
                "', Грейд='" + master.getGrade()+
                "', Стаж='" + master.getExperience()+
                "', Должность='" + master.getPost()+
                "', Образование='" + master.getEducation()+
                "', Описание='" + master.getDesc()+
                "', Почта='" + master.getEmail()+"' " +
                "  WHERE id='" + master.getId()+"';");
    }
    public void updateUser (String curEmail, String email)
    {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        Integer id = signInId();
        jdbc.execute("UPDATE [User] SET [login] = '"+curEmail+"' WHERE [User].[login] = '"+email+"'; ");
    }
    public void deleteUser(String email)
    {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        jdbc.execute("DELETE FROM [dbo].[User] WHERE [User].[login] = '"+email+"';");
    }
    public void deleteSer(int id)
    {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        jdbc.execute("DELETE FROM [dbo].[Service] WHERE [Service].[id] = '"+id+"';");
    }
    public boolean signInAdmin ()
    {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        var f = jdbc.queryForObject("SELECT COUNT ([login_in]) FROM [User] WHERE [User].[login_in] =1 AND [User].[id_role] = 2;",Integer.class);
        return f>0;
    }
    public int addService(Service service)
    {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        return jdbc.update(
                "INSERT INTO [dbo].[Service]" +
                        "           ([Название]" +
                        "           ,[Программа]" +
                        "           ,[Уровень_сложности]" +
                        "           ,[id_мастера]" +
                        "           ,[Описание]" +
                        "           ,[Стоимость])" +
                        "     VALUES" +
                        "           ('"+service.getName()+"'"+
                        "           ,'"+service.getProgram()+"'"+
                        "           ,'"+service.getLevel()+"'"+
                        "           ,'"+service.getId_master()+"'"+
                        "           ,'"+service.getDescription()+"'"+
                        "           ,'"+service.getPrice()+"')");

    }
    public Master getMaster(int id)
    {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        return jdbc.queryForObject("SELECT * FROM [dbo].[Master] WHERE [Master].[id] = '"+id+"';",new MasterMapper());
    }

}
