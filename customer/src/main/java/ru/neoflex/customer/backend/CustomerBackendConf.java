package ru.neoflex.customer.backend;
import io.eventuate.javaclient.spring.EnableEventHandlers;
import io.eventuate.jdbckafka.TramJdbcKafkaConfiguration;
import io.eventuate.AggregateRepository;
import io.eventuate.EventuateAggregateStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import ru.neoflex.customer.AccountAggregate;
import ru.neoflex.customer.backend.dao.DaoConfig;
import ru.neoflex.customer.command.AccountCommand;
import ru.neoflex.customer.eventhandler.EventHandlerCustomer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.StringVendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;




import javax.sql.DataSource;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * The  Customer backend configuration.
 */
@EnableEventHandlers
@EnableSwagger2
@Import({DaoConfig.class,TramJdbcKafkaConfiguration.class})
public class CustomerBackendConf {

    /**

     * Aggregate repository.
     *
     * @param eventStore the event store
     * @return the aggregate repository
     */
    @Bean
    public AggregateRepository<AccountAggregate, AccountCommand> aggregateRepository(EventuateAggregateStore eventStore) {
        return new AggregateRepository<>(AccountAggregate.class, eventStore);
    }

    /**
     * Event handler customer.
     *
     * @return the event handler customer
     */
    @Bean
    public EventHandlerCustomer eventHandler() {
        return new EventHandlerCustomer();
    }


    /**
     * Jdbc template jdbc template.
     *
     * @param dataSource the data source
     * @return the jdbc template
     */
    @Primary
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }


    /**
     * Management microservices web client.
     *
     * @param baseUrl the base url
     * @return the web client
     */
    @Bean(name = "management")
    public WebClient management(@Value("${management}") String baseUrl) {
        return WebClient.builder().baseUrl(baseUrl).build();
    }

    /**
     * Transaction web client.
     *
     * @param baseUrl the base url
     * @return the web client
     */
    @Bean(name = "transaction")
    public WebClient transaction(@Value("${transaction}") String baseUrl) {
        return WebClient.builder().baseUrl(baseUrl).build();
    }

    /**
     * Api docket.
     *
     * @return the docket
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("ru.neoflex.customer.web"))
                .paths(PathSelectors.any())
                .build().apiInfo(apiInfo()).useDefaultResponseMessages(false);
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Customer Microserivce",
                "Set of operations to work with customer aggregate",
                null,
                null,
                null,
                null,
                null,
                Stream.of(new StringVendorExtension("Company", "Neoflex")).collect(Collectors.toList())
        );
    }


}
