package ru.dmdev.spring_java_base.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import ru.dmdev.spring_java_base.connection_pool.ConnectionPool;
import ru.dmdev.spring_java_base.repository.CrudRepository;
import ru.dmdev.spring_java_base.repository.UserRepository;
import ru.dmdev.web.config.WebConfiguration;

@Import(WebConfiguration.class)
@PropertySource("classpath:application.properties")
@Configuration
@ComponentScan(basePackages = "ru.dmdev.spring_java_base",
        useDefaultFilters = false,
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Component.class),
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = CrudRepository.class),
                @ComponentScan.Filter(type = FilterType.REGEX,
                        pattern = "ru\\.dmdev\\.spring_java_base\\..+Repository")
        })
public class ApplicationConfiguration {

        @Bean
        public ConnectionPool pool1(@Value("${db.url}") String url) {
                return new ConnectionPool(url, 19);
        }

        @Bean
        public ConnectionPool pool2() {
                return new ConnectionPool("test-pool", 25);
        }

        @Bean
        @Profile("prod")
        public ConnectionPool pool3() {
                return new ConnectionPool("test-pool", 25);
        }

        @Bean
        @Profile("web")
        public UserRepository userRepository2(ConnectionPool pool2) {
                return new UserRepository(pool2);
        }

        @Bean
        @Profile("prod|web")
        public UserRepository userRepository3() {
                ConnectionPool connectionPool1 = pool2();
                ConnectionPool connectionPool2 = pool2();
                return new UserRepository(pool2());
        }
}
