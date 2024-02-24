package pu.master.tmsapi;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class TmsApiApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(TmsApiApplication.class, args);
    }

}
