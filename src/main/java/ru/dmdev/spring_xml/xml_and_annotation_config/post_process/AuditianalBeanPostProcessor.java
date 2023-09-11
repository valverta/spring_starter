package ru.dmdev.spring_xml.xml_and_annotation_config.post_process;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import ru.dmdev.spring_xml.xml_and_annotation_config.annotation.Auditianal;

import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class AuditianalBeanPostProcessor implements BeanPostProcessor, Ordered {

    private Map<String, Class<?>> auditianalBeans = new ConcurrentHashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(Auditianal.class)) {
            auditianalBeans.put(beanName, bean.getClass());
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = auditianalBeans.get(beanName);

        if (beanClass != null) {
            return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(),
                    (proxy, method, args) -> {
                        System.out.println("Audit method: " + method.getName());
                        long timeStart = System.currentTimeMillis();
                        try {
                            return method.invoke(bean, args);
                        } finally {
                            System.out.println("Time execution: " +
                                    (System.currentTimeMillis() - timeStart) + " millis");
                        }
                    });
        }

        return bean;
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
