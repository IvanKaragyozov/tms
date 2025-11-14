package pu.master.gui.configurations;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import pu.master.gui.configurations.utils.PackageNamesScans;


/**
 * Configuration class for defining component scanning in the application.
 * This class specifies the base packages to scan for components like controllers, services, validators, etc.
 */
@Configuration
@ComponentScan(basePackages = {
                PackageNamesScans.CORE_PACKAGES,
                PackageNamesScans.GUI_PACKAGES,
})
@EnableJpaRepositories(basePackages = PackageNamesScans.CORE_REPOSITORY_PACKAGE)
@EntityScan(basePackages = PackageNamesScans.DOMAIN_PACKAGES)
public class AppConfig {}
