package ru.dmdev.spring_xml.xml_and_annotation_config;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.dmdev.spring_xml.xml_and_annotation_config.model.TestingModel;
import ru.dmdev.spring_xml.xml_and_annotation_config.repository.Dao;
import ru.dmdev.spring_xml.xml_and_annotation_config.service.ServiceInterface;

public class MainTesting {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("testing.xml");

        ServiceInterface serviceTest = context.getBean(ServiceInterface.class);
        Dao dao = context.getBean(Dao.class);
        System.out.println(dao);

        TestingModel one = new TestingModel(1l, "Name");
        TestingModel save = serviceTest.save(one);
        System.out.println();
        TestingModel byId = serviceTest.getById(1l);
        System.out.println();
        System.out.println(save.toString() + "\n" + byId.toString() + "\n" + save.equals(byId));
        System.out.println();
        serviceTest.removeById(1l);
    }
}
