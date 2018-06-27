package ru.neoflex.management;

import io.eventuate.javaclient.driver.EventuateDriverConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.neoflex.management.backend.ManagementConf;

/**
 * The Management application.
 */
@SpringBootApplication
@Configuration
@Import({ManagementConf.class,EventuateDriverConfiguration.class})
@EnableAutoConfiguration
@ComponentScan
public class ManagementApplication {

	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(ManagementApplication.class, args);
	}
}
