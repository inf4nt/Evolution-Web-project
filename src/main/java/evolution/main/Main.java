package evolution.main;





import evolution.dao.MessageDao;
import evolution.dao.impl.MessageDaoImpl;
import evolution.model.Dialog;
import evolution.model.Message;
import evolution.model.SecretQuestionType;
import evolution.model.User;
import lombok.Getter;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import org.hibernate.query.Query;
import org.hibernate.type.LongType;


import java.math.BigInteger;
import java.util.Date;
import java.util.List;


/**
 * Created by Admin on 03.03.2017.
 */
public class Main {


    public static void main(String[] args) {
        SessionFactory sessionFactory = null;
        try {
            sessionFactory = getSessionFactory();
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();


            System.out.println("===============");
            System.out.println("===============");
            List list;
            Query query;


            MessageDao messageDao = new MessageDaoImpl(sessionFactory);
//            list = messageDao.findMessageByDialogAndAuthUserId(4, 226);


//            query = session.createQuery(
//                    " select new Message ( d.id , " +
//                    " m.id, m.message, m.dateDispatch," +
//                    " sender.id, sender.firstName, sender.lastName," +
//                    " im.id, im.firstName, im.lastName )" +
//                    " from Message m " +
//                    " join m.dialog as d " +
//                    " join m.sender as sender " +
//                    " join d.second as im " +
//                    " where d.first.id = 226l and d.second.id = 216l " +
//                    " order by m.dateDispatch asc ");



//            query = session.createQuery(
//                    " from Dialog d " +
//                    " where d.dialogPK.first.id = :first " +
//                    " and d.dialogPK.second.id = :second ");
//            query.setParameter("first", 226l);
//            query.setParameter("second", 1111l);
//
//
//            query.getSingleResult();


            System.out.println(messageDao.checkDialog(226, 2116));


//            list = query.list();
//            for (Object e : list)
//                System.out.println(e);
















            session.getTransaction().commit();
            sessionFactory.close();

        } catch (Exception e){
            e.printStackTrace();
            if (sessionFactory != null)
                sessionFactory.close();
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
