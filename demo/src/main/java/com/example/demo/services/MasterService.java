package com.example.demo.services;

import com.example.demo.configs.SqlServerJdbcConfig;
import com.example.demo.mapping.AdminMapper;
import com.example.demo.mapping.MasterMapper;
import com.example.demo.models.Admin;
import com.example.demo.models.Master;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

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
    public Master getLoginMaster()
    {
        String email = signInEmail();
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        return jdbc.queryForObject("SELECT * FROM [Master] WHERE [Master].[Почта] =  '"+email+"'",  new MasterMapper());
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
}
