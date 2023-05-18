package org.example;

import org.springframework.boot.SpringApplication;
import java.io.IOException;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages ="org.example")
public class Main
{
    public static void main(String[] args) throws IOException
    {
        SpringApplication.run(Main.class,args);
        openHomePage();
    }
    private static void openHomePage() throws  IOException {
        Runtime rt = Runtime.getRuntime();
        rt.exec("rundll32 url.dll,FileProtocolHandler " + "http://localhost:8080");
    }
}
