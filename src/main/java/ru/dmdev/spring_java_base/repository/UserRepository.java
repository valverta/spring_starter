package ru.dmdev.spring_java_base.repository;

import org.springframework.stereotype.Repository;
import ru.dmdev.spring_java_base.connection_pool.ConnectionPool;
import ru.dmdev.spring_java_base.entity.User;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository("repository")
public class UserRepository implements CrudRepository<Long, User> {

    private static final Map<Long, User> users = new ConcurrentHashMap();

    private final ConnectionPool pool1;

    public UserRepository(ConnectionPool pool1) {
        this.pool1 = pool1;
    }

    @Override
    public Optional<User> save(User user) {
        users.put(user.getId(), user);
        return Optional.of(user);
    }

    @Override
    public Optional<User> findById(long id) {
        return Optional.of(users.get(id));
    }

    @Override
    public void removeUserById(Long id) {
        users.remove(id);
    }
}
