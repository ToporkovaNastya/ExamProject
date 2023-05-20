package com.example.demo.services;
import com.example.demo.configs.SqlServerJdbcConfig;
import com.example.demo.mapping.RegistrationMapper;
import com.example.demo.models.Registration;
import org.springframework.beans.factory.annotation.Autowired;
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
                        "           ,[id_role])" +
                        "     VALUES" +
                        "           ('"+reg.getLogin()+"'"+
                        "           ,'"+reg.getPassword()+"'"+
                        "           ,'"+reg.getId_role()+"')");

    }

    public Registration findByLogin(String login) throws NullPointerException{
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        var reg = jdbc.queryForObject("SELECT * FROM [User] WHERE [User].[login] = '"+login+"';",  new RegistrationMapper());
        return reg;
    }
    public String getPassword(String password){
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        var reg = jdbc.queryForObject("SELECT  * [User] WHERE [User].[password] = "+password+";",  new RegistrationMapper());
        return reg.getPassword().toString();
    }
    public void getRole(int id){
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        var reg = jdbc.queryForObject("SELECT * FROM [User] WHERE [User].[id_role] = "+id+";",  new RegistrationMapper());
        System.out.println(reg.getId_role());
    }

}
