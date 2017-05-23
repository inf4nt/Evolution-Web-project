package evolution.dao.impl;

import evolution.dao.AdminDao;
import evolution.dao.MyQuery;
import evolution.model.user.User;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
public class AdminDaoImpl
        implements AdminDao {

    public AdminDaoImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<User> findAllUser() {
        return session().createQuery(FIND_USER_BY_ROLE_USER).list();
    }

    @Override
    public List<User> findAllAdmin() {
        return session().createQuery(FIND_USER_BY_ROLE_ADMIN).list();
    }

    @Override
    public List<User> findAllFieldsAll() {
        return session().createQuery("from User").list();
    }

    public Session session(){
        return sessionFactory.getCurrentSession();
    }

    @Autowired
    private SessionFactory sessionFactory;
}
