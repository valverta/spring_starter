package ru.dmdev.spring_xml.xml_and_annotation_config.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.dmdev.spring_xml.xml_and_annotation_config.model.TestingModel;

public class Connect {

//    @Value("${db.url}")
    private static final String url = "jdbc:postgresql://localhost:5433/postgres";

//    @Value("${db.user}")
    private static final String user = "postgres";

//    @Value("${db.password}")
    private static final String password = "postgres";

    public static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        configuration.setProperty("hibernate.connection.url", url);
        configuration.setProperty("hibernate.connection.username", user);
        configuration.setProperty("hibernate.connection.password", password);
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.format_sql", "true");
        configuration.setProperty("hibernate.transaction.auto_close_session", "true");
        configuration.setProperty("hibernate.current_session_context_class", "thread");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");

        configuration.addAnnotatedClass(TestingModel.class);

        return configuration.buildSessionFactory();
    }


}
