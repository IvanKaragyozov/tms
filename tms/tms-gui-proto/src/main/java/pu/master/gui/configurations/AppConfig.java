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
                PackageNamesScans.CORE_PACKAGES,
                PackageNamesScans.DOMAIN_PACKAGES,
                PackageNamesScans.GUI_PACKAGES,
                PackageNamesScans.REST_PACKAGES,
                PackageNamesScans.UI_TESTS_PACKAGES,
})
public class AppConfig {}
