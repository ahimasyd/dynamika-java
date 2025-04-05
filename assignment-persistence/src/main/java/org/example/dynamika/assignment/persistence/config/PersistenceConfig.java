package org.example.dynamika.assignment.persistence.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = {"org.example.dynamika.assignment.persistence.entity"})
@EnableJpaRepositories(basePackages = {"org.example.dynamika.assignment.persistence.repository"})
public class PersistenceConfig {

}
