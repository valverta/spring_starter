package ru.dmdev.spring_xml.xml_and_annotation_config.post_process;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import ru.dmdev.spring_xml.xml_and_annotation_config.annotation.Tranzakityanal;

import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TranzakityanalBeanPostProcessor implements BeanPostProcessor, Ordered {

    private Map<String, Class<?>> transactionBeans = new ConcurrentHashMap<>();

//    private final SessionFactory sessionFactory = Connect.getSessionFactory();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(Tranzakityanal.class)) {
            transactionBeans.put(beanName, bean.getClass());
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = transactionBeans.get(beanName);

        if (beanClass != null) {
            return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(),
                    (proxy, method, args) -> {
                        try {
                            System.out.println("Transaction start");
                            return method.invoke(bean, args);
                        } finally {
                            System.out.println("Transaction close");
                        }
                    });
        }

        return bean;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
