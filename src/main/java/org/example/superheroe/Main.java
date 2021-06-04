package org.example.superheroe;

import com.mangofactory.swagger.plugin.EnableSwagger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableSwagger
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
