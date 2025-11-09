package pu.master.rest.controllers;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import pu.master.rest.controllers.utils.PackageNamesScans;


@SpringBootApplication(scanBasePackages = {
                PackageNamesScans.CORE_PACKAGES,
                PackageNamesScans.DOMAIN_PACKAGES,
//                PackageNamesScans.GUI_PACKAGES,
                PackageNamesScans.REST_PACKAGES,
//                PackageNamesScans.UI_TESTS_PACKAGES,
})
@EnableJpaRepositories(basePackages = PackageNamesScans.CORE_REPOSITORY_PACKAGE)
public class TMSApplication
{
    public static void main(final String[] args)
    {
        SpringApplication.run(TMSApplication.class);
    }
}
