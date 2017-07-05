package evolution.main;





import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;


import evolution.model.Comment;
import evolution.model.Publication;
import evolution.model.user.StandardUser;
import org.hibernate.*;

import org.hibernate.cfg.Configuration;


import java.io.IOException;
import java.util.*;


/**
 * Created by Admin on 03.03.2017.
 */
public class Main {


    public static void main(String[] args) throws IOException {


        SessionFactory sessionFactory = null;
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

//            Publication publication = new Publication();
//            publication.setCategory(1L);
//            publication.setContent("СОДЕРЖАНИЕ");
//            publication.setStandardUser(new StandardUser(226L));
//            publication.setDate(new Date());
//            publication.setTheme("Theme");
//
//            session.save(publication);


            Comment comment = new Comment();
            comment.setContent("comment content2");
            comment.setDate(new Date());
            comment.setSender(new StandardUser(216L));
            comment.setPublication(new Publication(2L));

            session.save(comment);



            session.getTransaction().commit();
            sessionFactory.close();
        } catch (Exception e){
            e.printStackTrace();
            if (session != null && session.getTransaction() != null)
            session.getTransaction().rollback();
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