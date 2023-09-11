package ru.dmdev.spring_xml.post_process.bean_factory_post_processors;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;

import javax.annotation.PostConstruct;

public class TestOrderingBeanFactoryPostProcessor implements BeanFactoryPostProcessor, PriorityOrdered {

    public TestOrderingBeanFactoryPostProcessor() {
    }

    @PostConstruct
    private void init() {
        System.out.println();
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
//        BeanDefinition driver = beanFactory.getBeanDefinition("pool");
//        driver.setScope("prototype");
//        for (String s : driver.attributeNames()) {
//            System.out.print(s + ", ");
//        }
//        System.out.println();
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
