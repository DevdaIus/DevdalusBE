package site.devdalus.ariadne;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DevdalusApplication {

    public static void main(String[] args) {
        SpringApplication.run(DevdalusApplication.class, args);
    }

}
