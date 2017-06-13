package evolution.main;





import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

import evolution.dao.FeedDao;
import evolution.dao.impl.FeedDaoImpl;
import evolution.model.news.Feed;
import evolution.service.validation.Validator;
import org.hibernate.*;

import org.hibernate.cfg.Configuration;


import java.io.IOException;
import java.util.*;



/**
 * Created by Admin on 03.03.2017.
 */
public class Main {


    public static void main(String[] args) throws IOException {
//        String PathVariableId = new AntPathMatcher()
//                .extractPathWithinPattern( "/user/profile/**", "/user/profile/312" );


        SessionFactory sessionFactory = null;
        Validator validator = new Validator();
        Session session = null;
        try {
            sessionFactory = getSessionFactory();
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();


            System.out.println("===============");
            System.out.println("===============");
            List list;
            org.hibernate.query.Query query;
            Map map;





            FeedDao newsDao = new FeedDaoImpl(sessionFactory);

//            News news = new News("last news", new Date(), new User(226L));
//
//            newsDao.session().save(news);


//            newsDao.allPosts(226L, 100, 0).forEach(System.out::println);


            newsDao.repository().delete(new Feed(6L));








            session.getTransaction().commit();
            sessionFactory.close();
        } catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
            if (sessionFactory != null)
                sessionFactory.close();
        }





    }

    public static SessionFactory getSessionFactory(){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        return sessionFactory;
    }

    public static String objectToJson(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Hibernate5Module hbm = new Hibernate5Module();
        hbm.enable(Hibernate5Module.Feature.FORCE_LAZY_LOADING);
        mapper.registerModule(hbm);
        String jsonObject = mapper.writeValueAsString(obj);
        return jsonObject;
    }

    public static Object jsonToObject(String json, Class clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Object obj = mapper.readValue(json, clazz);
        return obj;
    }
}