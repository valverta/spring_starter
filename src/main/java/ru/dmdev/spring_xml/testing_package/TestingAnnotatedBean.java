package ru.dmdev.spring_xml.testing_package;

import javax.annotation.PostConstruct;

public class TestingAnnotatedBean {

//    @TestingAnnotation("My value annotation")
    private final String str;

    public TestingAnnotatedBean(String str) {
        this.str = str;
    }

    @PostConstruct
    private void init() {
        System.out.println();
    }

    public String getStr() {
        return str;
    }
}
