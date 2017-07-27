package evolution.main;





import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;


import evolution.model.user.StockUser;
import evolution.model.user.StandardUser;
import evolution.model.user.User;
import org.hibernate.*;

import org.hibernate.cfg.Configuration;


import java.io.IOException;


/**
 * Created by Admin on 03.03.2017.
 */
public class Main {


    public static void main(String[] args) throws IOException {


//        User user = new User(226L, "Maksim", "Lukaretskiy");
//
//        StandardUser standardUser = new StandardUser(226L, "Maksim", "Lukaretskiy");
//        StockUser stockUser1 = standardUser;
//        StockUser stockUser2 = user;
//        System.out.println(stockUser1.getId());
//        System.out.println(stockUser2.getId());



//        SessionFactory sessionFactory = null;
//        Session session = null;
//        try {
//            sessionFactory = getSessionFactory();
//            session = sessionFactory.getCurrentSession();
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
//            session.getTransaction().commit();
//            sessionFactory.close();
//        } catch (Exception e){
//            e.printStackTrace();
//            if (session != null && session.getTransaction() != null)
//            session.getTransaction().rollback();
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