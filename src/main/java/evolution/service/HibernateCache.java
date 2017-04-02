package evolution.service;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Admin on 02.04.2017.
 */
@Service
public class HibernateCache {

    @Autowired
    public HibernateCache(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public void reset () {
        sessionFactory.getCache().evictCollectionRegions();
        sessionFactory.getCache().evictEntityRegions();
    }

    private SessionFactory sessionFactory;
}
