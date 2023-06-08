package com.example.demo.services;

import com.example.demo.configs.SqlServerJdbcConfig;
import com.example.demo.mapping.AdminMapper;
import com.example.demo.mapping.MasterMapper;
import com.example.demo.mapping.ServiceMapper;
import com.example.demo.models.Admin;
import com.example.demo.models.Master;
import com.example.demo.models.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MasterService
{
    @Autowired
    private SqlServerJdbcConfig connection;
    public boolean signInMaster()
    {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        var f = jdbc.queryForObject("SELECT COUNT ([login_in]) FROM [User] WHERE [User].[login_in] =1 AND [User].[id_role] = 3;",Integer.class);
        return f>0;
    }
    public Integer signInRole ()
    {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        return jdbc.queryForObject("SELECT [id_role] FROM [User] WHERE [User].[login_in] =  1;",Integer.class);
    }
    public Master getLoginMaster()
    {
        String email = signInEmail();
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        return jdbc.queryForObject("SELECT * FROM [Master] WHERE [Master].[Почта] =  '"+email+"'",  new MasterMapper());
    }
    public boolean signIn ()
    {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        var f = jdbc.queryForObject("SELECT COUNT ([login_in]) FROM [User] WHERE [User].[login_in] =3;",Integer.class);
        return f>0;
    }
    public void signIn (String email)
    {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        Integer id = signInId(email);
        jdbc.execute("UPDATE [User] SET [login_in] = 1 WHERE [User].[login] = '"+email+"' AND [User].[id_role] = 3 AND [User].[id] = '"+id+"'; ");
    }
    public void signOut ()
    {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        Integer id = signInId();
        jdbc.execute("UPDATE [User] SET [login_in] = 0 WHERE [User].[id_role] = 3 AND [User].[id] = '"+id+"'; ");
    }
    public String signInEmail ()
    {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        return jdbc.queryForObject("SELECT [login] FROM [User] WHERE [User].[login_in] =  1 AND [User].[id_role] = 3;",String.class);
    }
    public int signInId (String email)
    {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        return jdbc.queryForObject("SELECT [id] FROM [User] WHERE [User].[login] = '"+email+"'AND [User].[id_role] = 3;",Integer.class);
    }
    public int signInId ()
    {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        return jdbc.queryForObject("SELECT [id] FROM [User] WHERE [User].[login_in] = 1 AND [User].[id_role] = 3;",Integer.class);
    }
    public List<Service> getServices(int id_master)
    {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        return jdbc.query("Select * FROM [dbo].[Service] WHERE [Service].[id_мастера] = '"+id_master+"';",new ServiceMapper());
    }
    public Master getFio(int id_master)
    {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        return jdbc.queryForObject("Select * FROM [dbo].[Master] WHERE [Master].[id] = "+id_master+";",new MasterMapper());
    }
}
