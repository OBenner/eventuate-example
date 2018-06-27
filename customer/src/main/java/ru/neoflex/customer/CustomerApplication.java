package ru.neoflex.customer;

import io.eventuate.javaclient.driver.EventuateDriverConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.neoflex.customer.backend.CustomerBackendConf;

@SpringBootApplication
@Configuration
@Import({CustomerBackendConf.class,EventuateDriverConfiguration.class})
@EnableAutoConfiguration
public class CustomerApplication {
	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}
}
