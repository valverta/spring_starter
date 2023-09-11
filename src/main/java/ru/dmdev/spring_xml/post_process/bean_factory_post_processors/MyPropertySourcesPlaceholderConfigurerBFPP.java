package ru.dmdev.spring_xml.post_process.bean_factory_post_processors;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.core.io.Resource;
import ru.dmdev.spring_xml.post_process.util.UtilityPostProcessors;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class MyPropertySourcesPlaceholderConfigurerBFPP extends UtilityPostProcessors
        implements BeanFactoryPostProcessor {

    public MyPropertySourcesPlaceholderConfigurerBFPP(final String... properties) {
        try {
            for (String property : properties) {
                InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(property);
                if (inputStream == null) {
                    throw new FileNotFoundException("File \"" + properties + "\" not found");
                }
                initPropertyMap(inputStream);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public MyPropertySourcesPlaceholderConfigurerBFPP(final Resource... locations) {
        try {
            for (Resource location : locations) {
                initPropertyMap(location.getInputStream());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void postProcessBeanFactory(final ConfigurableListableBeanFactory beanFactory) throws BeansException {

        for (String name : beanFactory.getBeanDefinitionNames()) {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(name);

            ConstructorArgumentValues cav = beanDefinition.getConstructorArgumentValues();

            List<ConstructorArgumentValues.ValueHolder> genericArgumentValues =
                    cav.getGenericArgumentValues();
            Map<Integer, ConstructorArgumentValues.ValueHolder> indexedArgumentValues =
                    cav.getIndexedArgumentValues();

            genericArgumentsValuesChanges(genericArgumentValues);

            indexedArgumentValuesChanges(indexedArgumentValues);
        }
    }

//    @Override
//    public int getOrder() {
//        return Ordered.LOWEST_PRECEDENCE;
//    }
}
