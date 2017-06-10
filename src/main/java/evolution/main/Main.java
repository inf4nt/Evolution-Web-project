package evolution.main;





import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

import evolution.service.validation.Validator;
import org.hibernate.*;

import org.hibernate.cfg.Configuration;
import org.springframework.util.AntPathMatcher;


import java.io.IOException;
import java.util.*;



/**
 * Created by Admin on 03.03.2017.
 */
public class Main {


    public static void main(String[] args) throws IOException {



        String PathVariableId = new AntPathMatcher()
                .extractPathWithinPattern( "/user/profile/**", "/user/profile/312" );





//        SessionFactory sessionFactory = null;
//        Validator validator = new Validator();
//        try {
//            sessionFactory = getSessionFactory();
//            Session session = sessionFactory.getCurrentSession();
//            session.beginTransaction();
//
//
//            System.out.println("===============");
//            System.out.println("===============");
//            List list;
//            org.hibernate.query.Query query;
//            Map map;
//
//
//
//
////            query = session.createQuery("select new MessageDTO(m.id, m.message, m.dateDispatch, " +
////                    " sender.id, sender.firstName, sender.lastName) " +
////                    " from MessageDTO m " +
////                    " join m.dialog as d " +
////                    " join m.sender as sender " +
////                    " where (d.first.id =:id1 and d.second.id =:id2 ) " +
////                    " or (d.first.id =:id2 and d.second.id =:id1 )");
////
////
////            query.setParameter("id1", 226L);
////            query.setParameter("id2", 217L);
////
////            query.list().forEach(System.out::println);
//
//
//
//
//
//
//
//            session.getTransaction().commit();
//            sessionFactory.close();
//        } catch (Exception e){
//            e.printStackTrace();
//            if (sessionFactory != null)
//                sessionFactory.close();
//        }



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