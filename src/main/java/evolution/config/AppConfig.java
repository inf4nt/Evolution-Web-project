package evolution.config;





import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.context.annotation.*;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import java.util.Properties;


@Configuration
@EnableWebMvc
@ComponentScan("evolution")
@Import({ SecurityConfig.class })
@EnableTransactionManagement
@EnableScheduling
public class AppConfig
        extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/view/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Bean
    public ObjectMapper objectMapper() {
//        ObjectMapper mapper = new ObjectMapper();
//        Hibernate5Module hbm = new Hibernate5Module();
//        hbm.enable(Hibernate5Module.Feature.FORCE_LAZY_LOADING);
//        mapper.registerModule(hbm);
//        return mapper;
        ObjectMapper mapper = new ObjectMapper();
        Hibernate5Module hbm = new Hibernate5Module();
        hbm.configure(Hibernate5Module.Feature.FORCE_LAZY_LOADING, false);
        mapper.registerModule(hbm);
        return mapper;
    }

    @Bean
    public JavaMailSender javaMailSender() {
        Properties mailProperties = new Properties();
        mailProperties.put("mail.smtp.auth", "true");
        mailProperties.put("mail.smtp.starttls.enable", "true");
        mailProperties.put("mail.debug", "true");

        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setPort(587);
        javaMailSender.setUsername("notification.evolution@gmail.com");
        javaMailSender.setPassword("77788877");
        javaMailSender.setProtocol("smtp");
        javaMailSender.setJavaMailProperties(mailProperties);

        return javaMailSender;
    }
}
