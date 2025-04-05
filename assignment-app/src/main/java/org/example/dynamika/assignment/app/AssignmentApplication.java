package org.example.dynamika.assignment.app;

import org.example.dynamika.assignment.persistence.config.PersistenceConfig;
import org.example.dynamika.assignment.service.config.ServiceConfig;
import org.example.dynamika.assignment.web.config.WebConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({PersistenceConfig.class, ServiceConfig.class, WebConfig.class})
public class AssignmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(AssignmentApplication.class, args);
    }

}
