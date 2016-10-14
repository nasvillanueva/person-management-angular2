package io.github.gediineko;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "io.github.gediineko.config")
public class PersonManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonManagementApplication.class, args);
    }
}
