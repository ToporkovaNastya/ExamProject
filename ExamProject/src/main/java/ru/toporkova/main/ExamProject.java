package ru.toporkova.main;

import java.io.IOException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication(scanBasePackages ="ru.toporkova.main")
public class ExamProject 
{
    public static void main(String[] args) throws Exception
    {
        SpringApplication.run(ExamProject.class,args); 
        openHomePage();
    }
    private static void openHomePage() throws IOException 
    {
       Runtime rt = Runtime.getRuntime();
       rt.exec("rundll32 url.dll,FileProtocolHandler " + "http://localhost:8080");
    }
}
