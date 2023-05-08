package ch.ffhs.admin.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"ch.ffhs.library.library.*", "ch.ffhs.admin.admin.*"})
@EnableJpaRepositories(value = "ch.ffhs.library.library.repository")
@EntityScan(value = "ch.ffhs.library.library.model")
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }

}
