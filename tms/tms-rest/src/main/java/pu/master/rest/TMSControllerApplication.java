package pu.master.rest;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import pu.master.rest.utils.PackageNamesScans;


@SpringBootApplication
//@ComponentScan(basePackages = {PackageNamesScans.CORE_REPOSITORY_PACKAGE,
//                               PackageNamesScans.CORE_SERVICE_PACKAGE,
//                               PackageNamesScans.CORE_MAPPER_PACKAGE,
//                               PackageNamesScans.CORE_JWT_PACKAGE,
//                               PackageNamesScans.CORE_CONFIGURATION_PACKAGE
//})
@ComponentScan(basePackages = PackageNamesScans.CORE_PACKAGES)
public class TMSControllerApplication
{

    public static void main(final String[] args)
    {
        SpringApplication.run(TMSControllerApplication.class);
    }
}
