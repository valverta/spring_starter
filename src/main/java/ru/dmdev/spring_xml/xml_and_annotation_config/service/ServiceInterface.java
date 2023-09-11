package ru.dmdev.spring_xml.xml_and_annotation_config.service;

import ru.dmdev.spring_xml.xml_and_annotation_config.model.TestingModel;

public interface ServiceInterface {

    TestingModel save(TestingModel model);

    TestingModel getById(Long id);

    void removeById(Long id);
}
