package org.tec.carpooling.app;

import javafx.application.Application;
import org.springframework.boot.SpringApplication; // Import for SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext; // Use ConfigurableApplicationContext
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("org.tec.carpooling.da.entities")
@EnableJpaRepositories("org.tec.carpooling.da.repositories")
public class CarpoolingSpringApp {

    // Static field to hold the Spring context
    private static ConfigurableApplicationContext springContext;

    public static void main(String[] args) {
        // The JavaFX application's launch will now handle Spring initialization.
        Application.launch(CarpoolingApplication.class, args);
    }

    // Getter for the context
    public static ConfigurableApplicationContext getSpringContext() {
        return springContext;
    }

    public static void setSpringContext(ConfigurableApplicationContext springContext) {
        CarpoolingSpringApp.springContext = springContext;
    }
}