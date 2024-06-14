package pu.master.gui.configurations;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pu.master.gui.utils.PackageNamesScans;


/**
 * Configuration class for defining component scanning in the application.
 * This class specifies the base packages to scan for components like controllers, services, validators, etc.
 */
@Configuration
@ComponentScan(basePackages = {
                PackageNamesScans.REST_CONTROLLER_PACKAGE,
                PackageNamesScans.GUI_VIEWS_PACKAGE,
                PackageNamesScans.CORE_CONFIGURATION_PACKAGE,
                PackageNamesScans.CORE_JWT_PACKAGE,
                PackageNamesScans.CORE_HANDLER_PACKAGE,
                PackageNamesScans.CORE_MAPPER_PACKAGE,
                PackageNamesScans.CORE_SERVICE_PACKAGE,
                PackageNamesScans.CORE_UTILS_PACKAGE,
                PackageNamesScans.CORE_VALIDATOR_PACKAGE
})
public class AppConfig {}
