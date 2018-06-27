package ru.neoflex.customertransaction;


import io.eventuate.jdbckafka.TramJdbcKafkaConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.neoflex.customertransaction.backend.ConfigurationTransaction;

//EventuateDriverConfiguration.class,
@SpringBootApplication
@Configuration
@Import({ConfigurationTransaction.class})
public class CustomerTransactionApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerTransactionApplication.class, args);
	}
}
