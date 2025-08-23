package ir.aria.pelaksefid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PelakSefidApplication {

    public static void main(String[] args) {
        SpringApplication.run(PelakSefidApplication.class, args);
    }

}
