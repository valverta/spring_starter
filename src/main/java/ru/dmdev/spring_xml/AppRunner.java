package ru.dmdev.spring_xml;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;
import ru.dmdev.spring_xml.repository.UserRepository;
import ru.dmdev.spring_xml.service.UserService;
import ru.dmdev.spring_xml.util.ConnectionPool;
import ru.dmdev.spring_xml.testing_package.TestingAnnotatedBean;

import java.io.FileNotFoundException;

public class AppRunner {
    public static void main(String[] args) throws FileNotFoundException {
//        Resource resource = new ClassPathResource("application.xml");
//        ConfigurableListableBeanFactory context = new XmlBeanFactory(resource);

//        DefaultListableBeanFactory context = new DefaultListableBeanFactory();
//        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(context);
//        beanDefinitionReader.loadBeanDefinitions("application.xml");
//
//        MyPropertySourcesPlaceholderConfigurerBFPP lbfpp =
//                new MyPropertySourcesPlaceholderConfigurerBFPP(new ClassPathResource("property.txt"));
//        lbfpp.postProcessBeanFactory(context);


//        CommonAnnotationBeanPostProcessor commonAnnotation =
//                new CommonAnnotationBeanPostProcessor();// @Autowired, @PostConstruct, @PreDestroy
//        AutowiredAnnotationBeanPostProcessor autowiredBPP =
//                new AutowiredAnnotationBeanPostProcessor();// Dependency Injection in constructor args
//        MyValueAnnotationBeanPostProcessor mvabpp = new MyValueAnnotationBeanPostProcessor();
//
//        context.addBeanPostProcessor(commonAnnotation);// @Autowired, @PostConstruct, @PreDestroy
//        context.addBeanPostProcessor(autowiredBPP);// Dependency Injection in constructor args
//        context.addBeanPostProcessor(mvabpp);


        //----
//        ConfigurationClassPostProcessor configurationClass =
//                new ConfigurationClassPostProcessor();// @Configuration class
//        PropertySourcesPlaceholderConfigurer propertySource = new PropertySourcesPlaceholderConfigurer();
//        propertySource.setLocation(new ClassPathResource("application.properties"));// @Value(${...})

//        configurationClass.postProcessBeanFactory(context);// @Configuration class
//        propertySource.postProcessBeanFactory(context);// @Value(${...})

//        EventListenerMethodProcessor elmp = new EventListenerMethodProcessor();//bfpp
//
//        elmp.postProcessBeanFactory((ConfigurableListableBeanFactory) context);

//-----------------------------------------------------------------------------------------------------------------

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml");

        UserRepository repository = context.getBean("repository", UserRepository.class);
        System.out.println(repository);

//        //AbstractRefreshableApplicationContext removeContext = (AbstractRefreshableApplicationContext) context;
//        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) context.getBeanFactory();

        ConnectionPool p3 = context.getBean("pool", ConnectionPool.class);
        System.out.println(p3);

        UserService service = context.getBean("service", UserService.class);

        TestingAnnotatedBean test = context.getBean("test", TestingAnnotatedBean.class);
        System.out.println(test.getStr());

        context.close();
        System.out.println(service);

//        context.close();

//        beanFactory.removeBeanDefinition("pool");

//        System.out.println(context.getBean("pool"));
    }

    private static void bla() {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(new FileSystemResource("beans.xml"));

// bring in some property values from a Properties file
        PropertySourcesPlaceholderConfigurer cfg = new PropertySourcesPlaceholderConfigurer();
        cfg.setLocation(new FileSystemResource("jdbc.properties"));

// now actually do the replacement
        cfg.postProcessBeanFactory(factory);


        // -----------------------------------------------

//        try {
//            UserService userService = new UserService();
//            userService.url = "url";
//
//            Field url = UserService.class.getDeclaredField("url");
//            url.setAccessible(true);
//            String s = (String) url.get(userService);
//
//            System.out.println(s);
//            System.out.println(userService);
//
//            url.set(userService, "not url");
//
//            System.out.println(userService);
//
//        } catch (NoSuchFieldException e) {
//            throw new RuntimeException(e);
//        } catch (IllegalAccessException e) {
//            throw new RuntimeException(e);
//        }

    }
}
