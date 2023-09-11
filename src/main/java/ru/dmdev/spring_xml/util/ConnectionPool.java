package ru.dmdev.spring_xml.util;

import org.springframework.core.AttributeAccessor;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Map;

public class ConnectionPool implements AttributeAccessor {

    private final String username;
    private final Integer port;
    private final List<String> list;
    private final Map<String, Object> map;

    public ConnectionPool(String username, Integer port, List<String> list, Map<String, Object> map) {
        this.username = username;
        this.port = port;
        this.list = list;
        this.map = map;
    }

    @PostConstruct
    private void init() {
        System.out.println();
    }

    @PreDestroy
    private void destroy() {
        System.out.println();
    }

    @Override
    public String
    toString() {
        return "ConnectionPool{" +
                "username='" + username + '\'' +
                ", port=" + port +
                ", list=" + list +
                ", map=" + map +
                '}';
    }

    @Override
    public void setAttribute(String name, Object value) {
        map.put(name, value);
    }

    @Override
    public Object getAttribute(String name) {
        return map.get(name);
    }

    @Override
    public Object removeAttribute(String name) {
        return map.remove(name);
    }

    @Override
    public boolean hasAttribute(String name) {
        return map.containsKey(name);
    }

    @Override
    public String[] attributeNames() {
        return map.keySet().toArray(String[]::new);
    }
}
