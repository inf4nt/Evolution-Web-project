package evolution.config;


import evolution.model.*;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

/**
 * Created by Admin on 07.03.2017.
 */

@Configuration
@EnableWebMvc
@ComponentScan("evolution")
@EnableTransactionManagement
public class HibernateServiceConfig
        implements TransactionManagementConfigurer {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://ec2-176-34-113-15.eu-west-1.compute.amazonaws.com:5432/d1dsfee517idaf?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory");
        dataSource.setUsername("blnjznffbkytsy");
        dataSource.setPassword("c119924e3525cce4b65884b042b6b83a6256f09e464b4767338d429d5adc6462");
        return dataSource;
    }

    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory());
        transactionManager.setDataSource(dataSource());
        transactionManager.setNestedTransactionAllowed(true);
        return transactionManager;
    }

    @Bean
    public SessionFactory sessionFactory() {
        return new LocalSessionFactoryBuilder(dataSource())
                .addAnnotatedClasses(SecretQuestionType.class)
                .addAnnotatedClasses(User.class)
                .addAnnotatedClasses(Friends.class)
                .addAnnotatedClasses(Dialog.class)
                .addAnnotatedClasses(Message.class)
                .addAnnotatedClasses(Message.MessageDialog.class)
                .setProperty("hibernate.show_sql", "true")
                .setProperty("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.EhCacheRegionFactory")
                .setProperty("hibernate.cache.use_query_cache", "true")
                .setProperty("hibernate.cache.use_second_level_cache", "true")
//                .setProperty("net.sf.ehcache.configurationResourceName", "/myehcache.xml")
                .setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL9Dialect")
                .setProperty("hibernate.enable_lazy_load_no_trans", "true")
                .buildSessionFactory();
    }
}
