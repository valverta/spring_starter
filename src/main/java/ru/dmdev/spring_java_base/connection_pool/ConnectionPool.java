package ru.dmdev.spring_java_base.connection_pool;


public class ConnectionPool {

    private String url;

    private int port;

    public ConnectionPool(String url, int port) {
        this.url = url;
        this.port = port;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getDriver() {
        return port;
    }

    public void setDriver(int port) {
        this.port = port;
    }
}
