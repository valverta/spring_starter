package ru.dmdev.spring_xml.service;

import ru.dmdev.spring_xml.repository.UserRepository;
import ru.dmdev.spring_xml.testing_package.TestingAnnotation;

public class UserService {

//    @Value("${db.url}")
    @TestingAnnotation("~[property.url]")
    public String url;


    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String toString() {
        return "UserService{" +
                "url='" + url + '\'' +
                ", userRepository=" + userRepository +
                '}';
    }
}
