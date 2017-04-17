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
    public List<User> searchByFistNameLastName(String p1, String p2, long authUserId) {
        hibernateSession = sessionFactory.getCurrentSession();
        Query query = hibernateSession.createQuery(MyQuery.SEARCH_BY_FIRST_AND_LAST_NAME);
        query.setParameter("id", authUserId);
        query.setParameter("p1", p1);
        query.setParameter("p2", p2);
        return query.list();
    }

    @Override
    public User findBySecretQuestionAndSecretQuestionType(long id, String secretQuestion, long sqtId) {
        hibernateSession = sessionFactory.getCurrentSession();
        Query query = hibernateSession.createQuery(MyQuery.FIND_USER_BY_SQ_AND_SQT_AND_ID);
        query.setParameter("id", id);
        query.setParameter("sqtId", sqtId);
        query.setParameter("sq", secretQuestion);
        return (User) query.getSingleResult();
    }

    @Override
    public List<User> searchByFistOrLastName(String like, long authUserId) {
        hibernateSession = sessionFactory.getCurrentSession();
        Query query = hibernateSession.createQuery(MyQuery.SEARCH_BY_FIRST_OR_LAST_NAME);
        query.setParameter("id", authUserId);
        query.setParameter("p1", like);
        return query.list();
    }

    @Override
    public User selectFirstLastName(long id) {
        hibernateSession = sessionFactory.getCurrentSession();
        Query query = hibernateSession.createQuery(MyQuery.SELECT_FIRST_LAST_NAME);
        query.setParameter("id", id);
        return (User) query.getSingleResult();
    }

    @Override
    public User findProfileAndFriendStatusById(long myId, long secondId) {
        hibernateSession = sessionFactory.getCurrentSession();
        Query query = hibernateSession.createQuery(MyQuery.FIND_PROFILE_AND_FRIEND_STATUS_BY_ID);
        query.setParameter("myId", myId);
        query.setParameter("secondId", secondId);
        return (User) query.getSingleResult();
    }

    private Session hibernateSession;
    @Autowired
    private SessionFactory sessionFactory;
}
