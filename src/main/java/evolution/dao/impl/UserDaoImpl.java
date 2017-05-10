package evolution.dao.impl;

import evolution.dao.MyQuery;
import evolution.dao.UserDao;
import evolution.model.SecretQuestionType;
import evolution.model.User;
import evolution.model.UserFriend;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 09.03.2017.
 */


@Repository
@Transactional
@NoArgsConstructor
public class UserDaoImpl
        implements UserDao {

    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(User user) {
        hibernateSession = sessionFactory.getCurrentSession();
        hibernateSession.save(user);
        Query query = hibernateSession.createQuery(UPDATE_USER);
        query.setParameter("id", user.getId());
        query.setParameter("fn", user.getFirstName());
        query.setParameter("ln", user.getLastName());
        query.setParameter("p", user.getPassword());
        query.setParameter("r", user.getRoleId());
        query.executeUpdate();
    }

    @Override
    public void update(User user) {
        hibernateSession = sessionFactory.getCurrentSession();
        hibernateSession.update(user);
    }

    @Override
    public void update(String username, String secretQuestion, long sqtId, String password) {
        hibernateSession = sessionFactory.getCurrentSession();
        Query query = hibernateSession.createQuery(UPDATE_FORGOT_PASSWORD);
        query.setParameter("password", password);
        query.setParameter("login", username);
        query.setParameter("sqtId", sqtId);
        query.setParameter("sq", secretQuestion);
        query.executeUpdate();
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
    public void delete(User user) {
        hibernateSession = sessionFactory.getCurrentSession();
        hibernateSession.delete(user);
    }

    @Override
    public List<User> findUserByFirstLastName(String p1, String p2) {
        hibernateSession = sessionFactory.getCurrentSession();
        Query query = hibernateSession.createQuery(FIND_USER_BY_FIRST_LAST_NAME);
        query.setParameter("p1", p1);
        query.setParameter("p2", p2);
        return query.list();
    }

    @Override
    public List<User> findUserByFirstOrLastName(String p1) {
        hibernateSession = sessionFactory.getCurrentSession();
        Query query = hibernateSession.createQuery(FIND_USER_BY_FIRST_OR_LAST_NAME);
        query.setParameter("p1", p1);
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
    public User findBySecretQuestionAndSecretQuestionType(String username, String secretQuestion, long sqtId) {
        hibernateSession = sessionFactory.getCurrentSession();
        Query query = hibernateSession.createQuery(MyQuery.FIND_USER_BY_SQ_AND_SQT_AND_USERNAME);
        query.setParameter("login", username);
        query.setParameter("sqtId", sqtId);
        query.setParameter("sq", secretQuestion);
        return (User) query.getSingleResult();
    }

    @Override
    public User selectFirstLastName(long id) {
        hibernateSession = sessionFactory.getCurrentSession();
        Query query = hibernateSession.createQuery(MyQuery.SELECT_FIRST_LAST_NAME);
        query.setParameter("id", id);
        return (User) query.getSingleResult();
    }

    @Override
    public User selectIdFirstLastName(long id) {
        hibernateSession = sessionFactory.getCurrentSession();
        Query query = hibernateSession.createQuery(MyQuery.SELECT_ID_FIRST_LAST_NAME);
        query.setParameter("id", id);
        return (User) query.getSingleResult();
    }

    @Override
    public UserFriend findUserAndFriendStatus(long myId, long secondId) {
        hibernateSession = sessionFactory.getCurrentSession();
        Query query = hibernateSession.createQuery(FIND_USER_AND_FRIEND_STATUS);
        query.setParameter("authUserId", myId);
        query.setParameter("userId", secondId);
        return (UserFriend) query.getSingleResult();
    }

    private Session hibernateSession;
    @Autowired
    private SessionFactory sessionFactory;
}
