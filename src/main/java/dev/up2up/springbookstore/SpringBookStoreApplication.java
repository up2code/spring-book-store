package dev.up2up.springbookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "dev.up2up.springbookstore")
public class SpringBookStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBookStoreApplication.class, args);
    }

}
