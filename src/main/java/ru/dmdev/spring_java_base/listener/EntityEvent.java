package ru.dmdev.spring_java_base.listener;

import ru.dmdev.spring_java_base.entity.User;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.EventObject;

public class EntityEvent<T extends User> extends EventObject {

    private final AccessType accessType;
    private final LocalDateTime timestamp;

    /**
     * Constructs a prototypical Event.
     *
     * @param entity     the object on which the Event initially occurred
     * @param accessType
     * @throws IllegalArgumentException if source is null
     */
    public EntityEvent(User entity, AccessType accessType) {
        super(entity);
        this.accessType = accessType;
        this.timestamp = LocalDateTime.now();
    }

    public AccessType getAccessType() {
        return accessType;
    }

    @Override
    public String toString() {
        return "EntityEvent{" +
                "accessType=" + accessType +
                ", source=" + source +
                ", source=" + timestamp +
                '}';
    }
}
