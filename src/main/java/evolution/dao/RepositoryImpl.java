package evolution.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Admin on 13.06.2017.
 */
@org.springframework.stereotype.Repository
@Transactional
public class RepositoryImpl implements Repository {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositoryImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Object object) {
        session().save(object);
    }

    @Override
    public void update(Object object) {
        session().update(object);
    }

    @Override
    public void delete(Object object) {
        session().delete(object);
    }

    @Override
    public Object merge(Object object) {
        return session().merge(object);
    }

    public Session session() {
        return sessionFactory.getCurrentSession();
    }

}
