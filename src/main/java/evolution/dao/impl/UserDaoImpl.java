package evolution.dao.impl;


import evolution.common.UserRoleEnum;
import evolution.dao.UserDao;
import evolution.model.user.User;
import lombok.Getter;
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

    private final static String FIND_USER_BY_USERNAME = "from User where login = :l";

    private final static String FIND_ALL_USER = " from User ";

    private final static String SELECT_ID_FIRST_LAST_NAME = "select new User(id, firstName, lastName) " + FIND_ALL_USER + " where id = :id";

    private final static String FIND_USER_BY_FIRST_LAST_NAME = "select new User(u.id, u.firstName, u.lastName )from User u " +
            " where (lower(u.firstName) like lower (concat('%', :p1, '%')) and lower(u.lastName) like lower(concat('%', :p2, '%'))) " +
            " or (lower(u.lastName) like lower (concat('%', :p1, '%')) and lower(u.firstName) like lower(concat('%', :p2, '%')))";

    private final static String FIND_USER_BY_FIRST_OR_LAST_NAME = " select new User(u.id, u.firstName, u.lastName )from User u " +
            " where (lower(u.firstName) like lower (concat('%', :p1, '%'))) or (lower(u.lastName) like lower(concat('%', :p1, '%')))";

    private final static String FIND_ALL_USER_ID_FIRST_LAST = "select new User(id, firstName, lastName) \n " + FIND_ALL_USER;

    private final static String FIND_USER_BY_ROLE_USER = FIND_ALL_USER_ID_FIRST_LAST + "\n where role_id = " + UserRoleEnum.USER.getId() +
            " order by id desc";

    private final static String FIND_USER_BY_ROLE_ADMIN = FIND_ALL_USER_ID_FIRST_LAST + "\n where role_id = " + UserRoleEnum.ADMIN.getId() +
            " order by id desc";

    private SessionFactory sessionFactory;

    @Autowired
    private evolution.dao.Repository repository;

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session session() {
        return sessionFactory.getCurrentSession();
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
    public List<User> findUserByFirstLastName(String p1, String p2, int limit, int offset) {
        Query query = session().createQuery(FIND_USER_BY_FIRST_LAST_NAME);
        query.setParameter("p1", p1);
        query.setParameter("p2", p2);
        query.setMaxResults(limit);
        query.setFirstResult(offset);
        return query.list();
    }

    @Override
    public List<User> findUserByFirstOrLastName(String p1, int limit, int offset) {
        Query query = session().createQuery(FIND_USER_BY_FIRST_OR_LAST_NAME);
        query.setParameter("p1", p1);
        query.setMaxResults(limit);
        query.setFirstResult(offset);
        return query.list();
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
    public List<User> findAll(int limit, int offset) {
        Query query = session().createQuery(FIND_ALL_USER);
        query.setMaxResults(limit);
        query.setFirstResult(offset);
        return query.list();
    }

    @Override
    public List<User> findAll() {
        return session().createQuery(FIND_ALL_USER_ID_FIRST_LAST).list();
    }

    @Override
    public evolution.dao.Repository repository() {
        return repository;
    }
}
