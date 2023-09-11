package ru.dmdev.spring_xml.xml_and_annotation_config.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.dmdev.spring_xml.xml_and_annotation_config.annotation.Auditianal;
import ru.dmdev.spring_xml.xml_and_annotation_config.annotation.Tranzakityanal;
import ru.dmdev.spring_xml.xml_and_annotation_config.model.TestingModel;
import ru.dmdev.spring_xml.xml_and_annotation_config.repository.Dao;
import ru.dmdev.spring_xml.xml_and_annotation_config.repository.DaoTesting;

import java.util.List;

@Component
@Auditianal
@Tranzakityanal
public class ServiceTesting implements ServiceInterface {

    //    @Resource(name = "dao1")
//    @Autowired
//    @Qualifier("dao1")
    private DaoTesting dao1;

    @Autowired
    private List<Dao> daos;

    @Autowired
    public void setDao1(DaoTesting dao1) {
        this.dao1 = dao1;
    }

//    public ServiceTesting(DaoTesting dao) {
//        this.dao = dao;
//    }

    @Override
    public TestingModel save(TestingModel model) {
//        return dao.save(model);
        System.out.println("Entity " + model.getName() + " is save!");
        return model;
    }

    @Override
    public TestingModel getById(Long id) {
//        return dao.getById(id);
        TestingModel testingModel = new TestingModel(id, "Name");
        System.out.println("Return model by id = " + id);
        return testingModel;
    }

    @Override
    public void removeById(Long id) {
//        dao.remove(getById(id));
        System.out.println("Remove model by id = " + id);
    }
}
