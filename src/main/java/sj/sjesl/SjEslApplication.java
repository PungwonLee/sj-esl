package sj.sjesl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SjEslApplication {

    public static void main(String[] args) {
        SpringApplication.run(SjEslApplication.class, args);
    }

}
