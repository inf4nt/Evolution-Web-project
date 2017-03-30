package evolution.config;


import evolution.model.SecretQuestionType;
import evolution.model.User;
import evolution.model.UserRole;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
        dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        dataSource.setUrl("jdbc:oracle:thin:@//localhost:1521/XE");
        dataSource.setUsername("db_test");
        dataSource.setPassword("db_test");
        return dataSource;




//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("org.postgresql.Driver");
//        dataSource.setUrl("jdbc:postgresql://ec2-54-75-226-64.eu-west-1.compute.amazonaws.com:5432/d60cr56j8414gg?ssl=true");
//        dataSource.setUsername("rpetbvmerkgrja");
//        dataSource.setPassword("2e1954c2346b841eb71cebc90f66f7a209a3b62d8980e9424b6aeba00e0cbda2");
//        return dataSource;
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
                .addAnnotatedClasses(UserRole.class)
                .setProperty("hibernate.show_sql", "true")
                .setProperty("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.EhCacheRegionFactory")
                .setProperty("hibernate.cache.use_query_cache", "true")
                .setProperty("hibernate.cache.use_second_level_cache", "true")
//                .setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect")
                .setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect")
                .setProperty("hibernate.enable_lazy_load_no_trans", "true")
                .buildSessionFactory();
    }
}
