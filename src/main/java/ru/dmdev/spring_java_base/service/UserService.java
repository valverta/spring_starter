package ru.dmdev.spring_java_base.service;

import ru.dmdev.spring_java_base.dto.UserDto;
import ru.dmdev.spring_java_base.entity.User;

import java.util.Optional;

public interface UserService {

    Optional<UserDto> save(User user);

    Optional<UserDto> findById(long id);

    void removeUserById(Long id);
}
