package pu.master.gui;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import pu.master.gui.utils.PackageNamesScans;


@Configuration
@ComponentScan(basePackages = {
                PackageNamesScans.CORE_PACKAGES,
                PackageNamesScans.DOMAIN_PACKAGES,
                PackageNamesScans.GUI_PACKAGES,
                PackageNamesScans.REST_PACKAGES,
                PackageNamesScans.UI_TESTS_PACKAGES,
})
@EnableJpaRepositories(basePackages = PackageNamesScans.CORE_REPOSITORY_PACKAGE)
public class TMSApplication
{
    public static void main(final String[] args)
    {
        SpringApplication.run(TMSApplication.class);
    }
}
