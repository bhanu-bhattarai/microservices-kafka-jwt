package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/** This annotation is composed of three annotation which are
 * @SpringBootConfiguration: This marks this class a spring configuration class
 * @EnableAutoConfiguration: Automatically configures Spring Boot features (like embedded Tomcat, Kafka, Security, etc.) based on classpath
 * @ComponentScan: Automatically scans for Spring-managed beans starting from this package downward
 */
@SpringBootApplication
public class OrderServiceApplication {
    public static void main(String[] args) {
        /**
         * what does the run method does?
         * Creates the Spring ApplicationContext (IoC container).
         * Performs classpath scanning for @Component, @Service, @RestController, etc.
         * Auto configures beans based on what’s on the classpath (e.g., Kafka, Security).
         * Starts embedded Tomcat server (or Jetty/Netty)
         * Triggers @PostConstruct, CommandLineRunner, ApplicationRunner beans.
         * Handles command-line args, logging, banners, profile selection, etc.
         */
        SpringApplication.run(OrderServiceApplication.class, args);
    }
}