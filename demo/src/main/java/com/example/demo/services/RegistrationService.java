package com.example.demo.services;
import com.example.demo.configs.SqlServerJdbcConfig;
import com.example.demo.mapping.RegistrationMapper;
import com.example.demo.models.Registration;
import com.example.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class RegistrationService {
    @Autowired
    private SqlServerJdbcConfig connection;

    public int addRegistration(Registration reg)
    {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        return jdbc.update(
                "INSERT INTO [dbo].[User]" +
                        "           ([login]" +
                        "           ,[password]" +
                        "           ,[id_role]" +
                        "           ,[login_in])" +
                        "     VALUES" +
                        "           ('"+reg.getLogin()+"'"+
                        "           ,'"+reg.getPassword()+"'"+
                        "           ,'"+reg.getId_role()+"'"+
                        "           ,'"+reg.getLogin_In()+"')");
    }
    public int getCount(String login){
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        var count = jdbc.queryForObject("SELECT COUNT(*) FROM [User] WHERE [User].[login] = '"+login+"';", Integer.class);
        return count;
    }
    public Registration findByLogin(String login) throws NullPointerException {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        var reg = jdbc.queryForObject("SELECT * FROM [User] WHERE [User].[login] = '"+login+"';",  new RegistrationMapper());
        return reg;
    }
    public String getPassword(String password){
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        var reg = jdbc.queryForObject("SELECT  * [User] WHERE [User].[password] = '"+password+"';",  new RegistrationMapper());
        return reg.getPassword().toString();
    }
    public void getRole(int id){
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        var reg = jdbc.queryForObject("SELECT * FROM [User] WHERE [User].[id_role] = '"+id+"';",  new RegistrationMapper());
        System.out.println(reg.getId_role());
    }

}
