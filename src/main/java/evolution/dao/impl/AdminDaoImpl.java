package evolution.dao.impl;

import evolution.dao.AdminDao;
import evolution.dao.MyQuery;
import evolution.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Admin on 02.04.2017.
 */
@Repository
@Transactional
public class AdminDaoImpl implements AdminDao {
    @Override
    public List<User> findLikeLogin(String like) {
        hibernateSession = sessionFactory.getCurrentSession();
        Query query = hibernateSession.createSQLQuery(MyQuery.FIND_USER_LIKE_LOGIN);
        query.setParameter("like", like);
        return query.list();
    }

    @Override
    public List<User> findAllUser() {
        hibernateSession = sessionFactory.getCurrentSession();
        return hibernateSession.createQuery(MyQuery.FIND_USER_BY_ROLE_USER).list();
    }

    @Override
    public List<User> findAllAdmin() {
        hibernateSession = sessionFactory.getCurrentSession();
        return hibernateSession.createQuery(MyQuery.FIND_USER_BY_ROLE_ADMIN).list();
    }

    private Session hibernateSession;
    @Autowired
    private SessionFactory sessionFactory;
}
