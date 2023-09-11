package ru.dmdev.spring_xml.xml_and_annotation_config.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DaoTesting implements Dao {

    @Value("${db.url}")
    private String string;

    @Override
    public String toString() {
        return "DaoTesting{" +
                "string='" + string + '\'' +
                "}\n";
    }

    //    private final SessionFactory sessionFactory = Connect.getSessionFactory();

//    public DaoTesting(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }

//    @Override
//    public TestingModel save(TestingModel model) {
//        return (TestingModel) sessionFactory.getCurrentSession().save(model);
//    }
//
//    @Override
//    public TestingModel getById(Long id) {
//        return sessionFactory.getCurrentSession().get(TestingModel.class, id);
//    }
//
//    @Override
//    public void remove(TestingModel model) {
//        sessionFactory.getCurrentSession().remove(model);
//    }
//
//    @Override
//    @PreDestroy
//    public void closeSessionFactory() {
//        sessionFactory.close();
//    }
}
