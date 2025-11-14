package pu.master.gui.controllers;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication()
//@EnableJpaRepositories(basePackages = PackageNamesScans.CORE_REPOSITORY_PACKAGE)
//@EntityScan(basePackages = PackageNamesScans.DOMAIN_PACKAGES)
public class TMSApplication
{
    public static void main(final String[] args)
    {
        SpringApplication.run(TMSApplication.class);
    }
}
