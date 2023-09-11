package ru.dmdev.spring_java_base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import ru.dmdev.spring_java_base.dto.UserDto;
import ru.dmdev.spring_java_base.entity.User;
import ru.dmdev.spring_java_base.listener.AccessType;
import ru.dmdev.spring_java_base.listener.EntityEvent;
import ru.dmdev.spring_java_base.repository.CrudRepository;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final CrudRepository<Long, User> repository;
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public UserServiceImpl(@Qualifier("repository") CrudRepository<Long, User> repository,
                           ApplicationEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public Optional<UserDto> save(User user) {
        return repository.save(user)
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.CREATE));
                    return new UserDto(entity.getId(), entity.getName());
                });
    }

    @Override
    public Optional<UserDto> findById(long id) {
        return repository.findById(id)
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));
                    return new UserDto(entity.getId(), entity.getName());
                });
    }

    @Override
    public void removeUserById(Long id) {
        repository.removeUserById(id);
    }
}
