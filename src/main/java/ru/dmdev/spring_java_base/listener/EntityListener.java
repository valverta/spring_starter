package ru.dmdev.spring_java_base.listener;

import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
public class EntityListener {

    @EventListener(condition = "#root.args[0].getAccessType().name() == 'READ'")
    @Order(10)
    public void acceptEntityRead(EntityEvent entityEvent) {
        System.out.println("Entity: " + entityEvent);
    }

    @EventListener(condition = "#a0.accessType.name() == 'CREATE'")
    @Order(10)
    public void acceptEntityCreate(EntityEvent entityEvent) {
        System.out.println("Entity: " + entityEvent);
    }
}
