package ru.dmdev.spring_xml.post_process.bean_post_processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import ru.dmdev.spring_xml.testing_package.TestingAnnotation;
import ru.dmdev.spring_xml.post_process.util.UtilityPostProcessors;

import java.lang.reflect.Field;

public class MyValueAnnotationBeanPostProcessor extends UtilityPostProcessors
        implements BeanPostProcessor, PriorityOrdered
{

    public MyValueAnnotationBeanPostProcessor() {}

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Field[] declaredFields = bean.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(TestingAnnotation.class)) {
                TestingAnnotation annotation = field.getAnnotation(TestingAnnotation.class);
                field.setAccessible(true);
                try {
                    field.set(bean, newValue(annotation.value()));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return bean;

//        for (String name : beanFactory.getBeanDefinitionNames()) {
//            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(name);
//            try {
//                Field[] declaredFields = Class.forName(beanDefinition.getBeanClassName()).getDeclaredFields();
//                for (Field field : declaredFields) {
//                    if (field.isAnnotationPresent(TestingAnnotation.class)) {
//                        TestingAnnotation test = field.getAnnotation(TestingAnnotation.class);
//                        field.setAccessible(true);
//                        try {
//                            field.set(beanFactory, test.value());
//                        } catch (IllegalAccessException e) {
//                            throw new RuntimeException(e);
//                        }
//                    }
//                }
//            } catch (ClassNotFoundException e) {
//                throw new RuntimeException(e);
//            }
//        }
    }
}
