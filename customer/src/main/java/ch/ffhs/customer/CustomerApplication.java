package ch.ffhs.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * CustomerApplication which handels the library and customer packages
 * and starts the Spring application with the main() method
 *
 */
@SpringBootApplication(scanBasePackages = {"ch.ffhs.customer", "ch.ffhs.library"})
@EnableJpaRepositories(value = "ch.ffhs.library.library.repository")
@EntityScan(value = "ch.ffhs.library.library.model")
public class CustomerApplication {

    /**
     * main() method to start application
     */
    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }

}
