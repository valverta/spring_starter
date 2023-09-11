package ru.dmdev.spring_xml.repository;

import ru.dmdev.spring_xml.util.ConnectionPool;

public class UserRepository {

    private final ConnectionPool connectionPool;

    private UserRepository(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public static UserRepository of(ConnectionPool connectionPool) {
        return new UserRepository(connectionPool);
    }
}
