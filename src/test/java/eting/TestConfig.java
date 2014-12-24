package eting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import javax.sql.DataSource;

/**
 * Created by lifenjoy51 on 12/3/14.
 */
@Profile("test")
@ComponentScan
@Configuration
@EnableAutoConfiguration
public class TestConfig {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(TestConfig.class);
    }

    @Bean
    public StandardPasswordEncoder standardPasswordEncoder(){
        return new StandardPasswordEncoder("eting");
    }

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL).build();
    }
}
