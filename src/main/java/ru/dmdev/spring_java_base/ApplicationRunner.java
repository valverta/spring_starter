package ru.dmdev.spring_java_base;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.dmdev.spring_java_base.config.ApplicationConfiguration;
import ru.dmdev.spring_java_base.dto.UserDto;
import ru.dmdev.spring_java_base.entity.User;
import ru.dmdev.spring_java_base.service.UserService;

import java.util.Optional;

public class ApplicationRunner {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ApplicationConfiguration.class)) {

            UserService userService = context.getBean(UserService.class);

            User user = new User();
            user.setId(1);
            user.setName("Name");

            Optional<UserDto> save = userService.save(user);
            Optional<UserDto> byId = userService.findById(user.getId());

            System.out.println(save.get().equals(byId.get()));
        }
    }
}
