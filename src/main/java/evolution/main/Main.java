package evolution.main;





import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import evolution.dao.FriendsDao;
import evolution.dao.impl.FriendsDaoImpl;
import evolution.model.jsonModel.JsonInformation;
import evolution.model.user.User;

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
        try {
            sessionFactory = getSessionFactory();
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();


            System.out.println("===============");
            System.out.println("===============");
            List list;
            Map map;
            Query query;


            FriendsDao friendsDao = new FriendsDaoImpl(sessionFactory);
            System.out.println(friendsDao.checkFriends(226, 106));






            session.getTransaction().commit();
            sessionFactory.close();
        } catch (Exception e){
            e.printStackTrace();
            if (sessionFactory != null)
                sessionFactory.close();


        }



    }

    public static boolean check(Class clazz) {
        if (clazz == User.class)
            return true;
        else return false;
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


    public static List jsonToListUser(String json) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<User>> mapType = new TypeReference<List<User>>() {};
        List<User> jsonToPersonList = objectMapper.readValue(json, mapType);
        return jsonToPersonList;
    }

}
















































//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        System.out.println("run");
//        while (true) {
//            String console = bufferedReader.readLine();
//            if (console.matches("\\d+")) {
//                System.out.println("ok");
//            } else
//                System.out.println("false");
//
//            if (console.matches("[a-z]+")) {
//                System.out.println("true");
//            } else
//                System.out.println("false");
//
//        }



//        Query query = session.createSQLQuery("select\n" +
//                "  u.id,\n" +
//                "  u.FIRST_NAME,\n" +
//                "  u.LAST_NAME\n" +
//                "  from friends f\n" +
//                "  join USER_DATA u on f.friend_id = u.id\n" +
//                "  join USER_ROLE ur on u.ROLE_ID = ur.ID\n" +
//                "  join SECRET_QUESTION_TYPE sqt on u.SECRET_QUESTION_TYPE_ID = sqt.ID");
//
//        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
//        list = query.list();
//
//        for (Object e: list)
//            System.out.println(e);



//        Query query = session.createSQLQuery("select id, login from user_data");
//        List<Object[]> rows = query.list();
//
//        for(Object[] row : rows){
//            User user = new User();
//            user.setId(Long.parseLong(row[0].toString()));
//            System.out.println(user);
//        }
