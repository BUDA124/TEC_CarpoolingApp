package org.tec.carpooling.app;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "org.tec.carpooling.da.repositories")
@EntityScan(basePackages = "org.tec.carpooling.da.entities")
@ComponentScan(basePackages = {"org.tec.carpooling.app", "org.tec.carpooling.bl", "org.tec.carpooling.da",
                                "org.tec.carpooling.ui.controllers"})
public class CarpoolingSpringApp {

    // Static reference to the Spring Context
    private static ConfigurableApplicationContext context;

    // Method to set the context (called by CarpoolingApplication)
    public static void setSpringContext(ConfigurableApplicationContext springContext) {
        context = springContext;
    }

    // Method to get the context if needed (use with caution, ideally inject beans)
    public static ConfigurableApplicationContext getApplicationContext() {
        return context;
    }

    public static void main(String[] args) {
        Application.launch(CarpoolingApplication.class, args);
    }
}