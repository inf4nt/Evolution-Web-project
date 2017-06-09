package evolution.dao.impl;


import evolution.common.UserRoleEnum;
import evolution.dao.UserDao;
import evolution.model.user.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 09.03.2017.
 */


@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    private SessionFactory sessionFactory;

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(User user) {
        session().save(user);
    }

    @Override
    public void update(User user) {
        session().update(user);
    }

    @Override
    public void delete(User user) {
        session().delete(user);
    }

    @Override
    public User find(Long id) {
        return session().find(User.class, id);
    }

    @Override
    public User findByLogin(String login) {
        Query query = session().createQuery(FIND_USER_BY_USERNAME);
        query.setParameter("l", login);
        return (User) query.getSingleResult();
    }

    @Override
    public List<User> findUserByFirstLastName(String p1, String p2) {
        Query query = session().createQuery(FIND_USER_BY_FIRST_LAST_NAME);
        query.setParameter("p1", p1);
        query.setParameter("p2", p2);
        return query.list();
    }

    @Override
    public List<User> findUserByFirstOrLastName(String p1) {
        Query query = session().createQuery(FIND_USER_BY_FIRST_OR_LAST_NAME);
        query.setParameter("p1", p1);
        return query.list();
    }

    @Override
    public List<User> findUserByFirstLastName(String p1, String p2, int limit) {
        Query query = session().createQuery(FIND_USER_BY_FIRST_LAST_NAME);
        query.setParameter("p1", p1);
        query.setParameter("p2", p2);
        query.setMaxResults(limit);
        return query.list();
    }

    @Override
    public List<User> findUserByFirstOrLastName(String p1, int limit) {
        Query query = session().createQuery(FIND_USER_BY_FIRST_OR_LAST_NAME);
        query.setParameter("p1", p1);
        query.setMaxResults(limit);
        return query.list();
    }

    @Override
    public User selectFirstLastName(long id) {
        Query query = session().createQuery(SELECT_FIRST_LAST_NAME);
        query.setParameter("id", id);
        return (User) query.getSingleResult();
    }

    @Override
    public User selectIdFirstLastName(long id) {
        Query query = session().createQuery(SELECT_ID_FIRST_LAST_NAME);
        query.setParameter("id", id);
        return (User) query.getSingleResult();
    }

    @Override
    public List<User> findAllUser(int limit, int offset) {
        Query query = session().createQuery(FIND_USER_BY_ROLE_USER);
        query.setMaxResults(limit);
        query.setFirstResult(offset);
        return query.list();
    }

    @Override
    public List<User> findAllAdmin(int limit, int offset) {
        Query query = session().createQuery(FIND_USER_BY_ROLE_ADMIN);
        query.setMaxResults(limit);
        query.setFirstResult(offset);
        return query.list();
    }

    @Override
    public Map<String, List<User>> findAll(int limit, int offset) {
        Map<String, List<User>> map = new HashMap<>();

        map.put(UserRoleEnum.USER.toString().toLowerCase(), findAllUser(limit, offset));
        map.put(UserRoleEnum.ADMIN.toString().toLowerCase(), findAllAdmin(limit, offset));

        return map;
    }

    public Session session(){
        return sessionFactory.getCurrentSession();
    }
}
