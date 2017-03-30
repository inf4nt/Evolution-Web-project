package evolution.main;





import evolution.common.FriendStatusEnum;
import evolution.common.RestStatus;
import evolution.common.UserRoleEnum;
import evolution.dao.FriendsDao;
import evolution.dao.impl.FriendsDaoImpl;
import evolution.dao.impl.UserDaoImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Admin on 03.03.2017.
 */
public class Main {

//    public static void main(String[] args) throws IOException {
//
//        Session session = null;
//        try {
//
//            session = getSessionFactory().getCurrentSession();
//            session.beginTransaction();
////            ВАРИАНТ 1
////            ВАРИАНТ 1
////            ВАРИАНТ 1
//
////            String sql = "select u.* from USER_DATA u\n" +
////                    "  join friends f on u.id = f.friend_id\n" +
////                    "  join USER_ROLE ur on u.ROLE_ID = ur.ID\n" +
////                    "  left join SECRET_QUESTION_TYPE sqt on u.SECRET_QUESTION_TYPE_ID = sqt.ID\n" +
////                    "where f.status != 'progress'";
//
////            Query query = session.createSQLQuery(sql)
////                    .addEntity(User.class);
//
////            List list = query.list();
////            for (Object e : list)
////                System.out.println(e);
//
//
//
//
//
//
//
////            ВАРИАНТ 2
////            ВАРИАНТ 2
////            ВАРИАНТ 2
////            String sql = "select u.id, u.first_name,u.last_name from USER_DATA u\n" +
////                    "  join friends f on u.id = f.friend_id\n" +
////                    "  join USER_ROLE ur on u.ROLE_ID = ur.ID\n" +
////                    "  left join SECRET_QUESTION_TYPE sqt on u.SECRET_QUESTION_TYPE_ID = sqt.ID\n" +
////                    "where f.status != 'progress'";
////            Query query = session.createSQLQuery(sql);
////
//////            List list = query.list();
//////            for (Object a: list){
//////                Object rows[] = (Object[]) a;
//////                User user = new User();
//////                user.setId(Long.parseLong(rows[0].toString()));
//////                user.setFirstName(rows[1].toString());
//////                user.setLastName(rows[2].toString());
//////                System.out.println(user);
//////            }
////
////            List list = new UserDaoImpl.UserMapper().listUser(query.list());
////
////            for (Object e : list)
////                System.out.println(e);
//
//
//
////            select *
////                    from  USER_DATA WHERE id in
////            (
////                        SELECT f.FRIEND_ID from USER_DATA u join FRIENDS f on u.ID = f.USER_ID and f.USER_ID = 8118
////                        UNION
////                        SELECT f.USER_ID  from USER_DATA u join FRIENDS f on u.ID = f.FRIEND_ID and f.FRIEND_ID = 8118
////            );
//
//
//
////            FriendsDaoImpl friendsDao = new FriendsDaoImpl(session);
////
////            List list = friendsDao.findMyFriend(102l);
////
////            for (Object e:list)
////                System.out.println(e);
////
////            list = friendsDao.findMyFollower(8122l);
////
////            for (Object e:list)
////                System.out.println(e);
//
//            if (session != null)
//            session.getTransaction().commit();
//        }
//        catch (Exception e){
//            if (session != null)
//                session.getTransaction().rollback();
//            e.printStackTrace();
//        }
//
//        finally {
//            if (session != null)
//                session.getSessionFactory().close();
//        }
//    }


    public static void main(String[] args) {

//       Session session = getSessionFactory().getCurrentSession();
//       session.beginTransaction();
//       System.out.println("transaction");
//
//
//
//       session.getSessionFactory().close();



    }


    public static SessionFactory getSessionFactory(){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        return sessionFactory;
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
