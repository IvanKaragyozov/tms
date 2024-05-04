package pu.master.rest;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import pu.master.rest.utils.PackageNamesScans;


@SpringBootApplication
@EntityScan(basePackages = PackageNamesScans.DOMAIN_ENTITY_PACKAGE)
@EnableJpaRepositories(basePackages = PackageNamesScans.CORE_REPOSITORY_PACKAGE)
@ComponentScan(basePackages = {PackageNamesScans.CORE_SERVICE_PACKAGE,
                               PackageNamesScans.CORE_CONFIGURATION_PACKAGE,
                               PackageNamesScans.CORE_MAPPER_PACKAGE,
                               PackageNamesScans.CORE_JWT_PACKAGE
})
public class TMSControllerApplication
{

    public static void main(final String[] args)
    {
        SpringApplication.run(TMSControllerApplication.class);
    }
}
