package org.example.dynamika.assignment.web.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "org.example.dynamika.assignment.web.mvc",
        "org.example.dynamika.assignment.web.rest",
        "org.example.dynamika.assignment.web.advice"
})
public class WebConfig {

    @Bean
    OpenAPI getOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Dynamika Assignment API")
                        .version("v1.0.0")
                );
    }

}
