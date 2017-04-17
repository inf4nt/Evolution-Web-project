package evolution.main;





import evolution.common.FriendStatusEnum;
import evolution.dao.MyQuery;
import evolution.model.Friends;
import evolution.model.User;
import evolution.service.SearchService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;


/**
 * Created by Admin on 03.03.2017.
 */
public class Main {


    public static void main(String[] args) {

        try {
            SessionFactory sessionFactory = getSessionFactory();
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();


            System.out.println("===============");
            System.out.println("===============");

//            Query query = session.createQuery("select new User (u.id, u.firstName, u.lastName, f.status) " +
//                    "from User u " +
//                    "left join Friends f on f.friendId.id = u.id and f.userId.id = 226l " +
//                    "where (lower(u.firstName) like lower (concat('%', :p1, '%')) and lower(u.lastName) like lower(concat('%', :p2, '%'))) " +
//                    "or (lower(u.lastName) like lower (concat('%', :p1, '%')) and lower(u.firstName) like lower(concat('%', :p2, '%')))");
//
//
//            Query query = session.createQuery("select new User (u.id, u.firstName, u.lastName, f.status) " +
//                    "from User u " +
//                    "left join Friends f on f.friendId.id = u.id and f.userId.id = :id " +
//                    "where (lower(u.firstName) like lower (concat('%', :p1, '%'))) or (lower(u.lastName) like lower(concat('%', :p1, '%')))");
//
//            query.setParameter("id", 226l);
//            query.setParameter("p1", "melnichuk");
//
//
//            List list = query.list();
//
//            for (Object e : list)
//                System.out.println(e);


//            Query query = session.createQuery("select new User (u, sqt, f.status) " +
//                    "from User as u " +
//                    " join SecretQuestionType as sqt on sqt.id = u.secretQuestionType.id"  +
//                    " left join Friends as f on f.friendId.id = u.id and f.userId.id = 226l where u.id = 216l");


//            Query query = session.createQuery("select new User (u) from User as u " +
//                    " where u.id = :id");


            Query query = session.createQuery("select new User (u.id, u.firstName, u.lastName, u.roleId, u.registrationDate, f.status) " +
                    " from User as u " +
                    " left join Friends as f on f.friendId.id = u.id and f.userId.id = 226l " +
                    " where u.id = 55555555l");


            List list = query.list();

            for (Object e : list)
                System.out.println(e);






            session.getTransaction().commit();
            sessionFactory.close();






        } catch (Exception e){
            e.printStackTrace();
        }
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
