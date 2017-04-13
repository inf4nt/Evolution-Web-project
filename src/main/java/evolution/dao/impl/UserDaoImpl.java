package evolution.dao.impl;

import evolution.dao.MyQuery;
import evolution.dao.UserDao;
import evolution.model.SecretQuestionType;
import evolution.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 09.03.2017.
 */
@Repository
@Transactional
public class UserDaoImpl
        implements UserDao {

    @Override
    public void save(User user) {
        hibernateSession = sessionFactory.getCurrentSession();
        hibernateSession.save(user);
    }

    @Override
    public void update(User user) {
        hibernateSession = sessionFactory.getCurrentSession();
        hibernateSession.update(user);
    }

    @Override
    public User findById(long id) {
        hibernateSession = sessionFactory.getCurrentSession();
        return  hibernateSession.find(User.class, id);
    }

    @Override
    public User findByLogin(String login) {
        hibernateSession = sessionFactory.getCurrentSession();
        Query query = hibernateSession.createQuery(MyQuery.FIND_USER_BY_LOGIN);
        query.setParameter("l", login);
        return (User) query.getSingleResult();
    }

    @Override
    public void deleteById(long id) {
        hibernateSession = sessionFactory.getCurrentSession();
        Query query = hibernateSession.createQuery(MyQuery.DELETE_USER_BY_ID);
        query.setParameter("i", id);
        query.executeUpdate();
    }

    @Override
    public void delete(User userEvolution) {
        hibernateSession = sessionFactory.getCurrentSession();
        hibernateSession.delete(userEvolution);
    }

    @Override
    public List<User> searchByFistNameLastName(String like, long authUserId) {
        String regex[] = like.split(" ");
        hibernateSession = sessionFactory.getCurrentSession();
        Query query = hibernateSession.createSQLQuery(MyQuery.SEARCH_BY_FIRST_LAST_NAME);
        query.setParameter("auth_user_id", authUserId);
        query.setParameter("p1", regex[0]);
        query.setParameter("p2", regex[1]);
        return new UserMapperForFriends().listUser(query.list());
    }

    @Override
    public User findBySecretQuestionAndSecretQuestionType(long id, String secretQuestion, long sqtId) {
        hibernateSession = sessionFactory.getCurrentSession();
        Query query = hibernateSession.createSQLQuery(MyQuery.FIND_USER_BY_SQ_AND_SQT_AND_ID)
                .addEntity(User.class)
                .setParameter("sq", secretQuestion)
                .setParameter("sqtId", sqtId)
                .setParameter("id", id);
        return (User) query.getSingleResult();
    }

    public static class UserMapperForFriends {

        public User mapper (Object a){
            User user = new User();
            Object rows[] = (Object[]) a;
            user.setId(Long.parseLong(rows[0].toString()));
            user.setFirstName(rows[1].toString());
            user.setLastName(rows[2].toString());
            try {
                user.setFriendStatus(rows[3].toString());
            } catch (ArrayIndexOutOfBoundsException e){}
            return user;
        }

        public List<User> listUser (List list) {
            System.out.println(list.size());
            List result = new ArrayList();
            for (Object a: list)
                result.add(mapper(a));
            return result;
        }
    }



    private Session hibernateSession;
    @Autowired
    private SessionFactory sessionFactory;
}
