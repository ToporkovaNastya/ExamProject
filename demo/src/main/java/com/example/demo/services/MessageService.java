package com.example.demo.services;

import com.example.demo.configs.SqlServerJdbcConfig;
import com.example.demo.mapping.MessageMapper;
import com.example.demo.mapping.UserMapper;
import com.example.demo.models.Message;
import com.example.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MessageService
{
    @Autowired
    private SqlServerJdbcConfig connection;
    public List<Message> getMessage(int id){
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        return jdbc.query("SELECT * FROM [Message] WHERE [Message].[id_пользователя] = "+id+";",  new MessageMapper());
    }
    public Message getUserMessage(int id){
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        return jdbc.queryForObject("SELECT * FROM [Message] WHERE [Message].[id] = "+id+";",  new MessageMapper());
    }
    public int addMessage(Message message)
    {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        return jdbc.update(
                "INSERT INTO [dbo].[Message]" +
                        "           ([id_записи]" +
                        "           ,[id_пользователя]" +
                        "           ,[message])" +
                        "     VALUES" +
                        "           ('"+message.getApp_id()+"'"+
                        "           ,'"+message.getUser_id()+"'"+
                        "           ,'"+message.getMessage()+"')");

    }
    public void deleteMessage(int id)
    {
        var jdbc = new JdbcTemplate(connection.mysqlDataSource());
        jdbc.execute("DELETE FROM [dbo].[Message] WHERE [Message].[id] = "+id+";");
    }
}
