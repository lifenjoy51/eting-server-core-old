package eting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by lifenjoy51 on 12/3/14.
 */
@ComponentScan
@Configuration
@EnableAutoConfiguration
public class EtingApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(EtingApplication.class);
    }
}
