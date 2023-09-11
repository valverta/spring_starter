package ru.dmdev.spring_java_base.repository;

import ru.dmdev.spring_java_base.entity.User;

import java.util.Optional;

public interface CrudRepository<K, V> {

    Optional<User> save(User user);

    Optional<User> findById(long id);

    void removeUserById(Long id);
}
