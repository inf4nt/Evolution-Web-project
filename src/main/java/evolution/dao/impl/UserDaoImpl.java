package evolution.dao.impl;


import evolution.dao.UserDao;
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
 * Created by Admin on 09.03.2017.
 */


@Repository
@Transactional
@NoArgsConstructor
public class UserDaoImpl
        implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

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
    public void delete(User user) {
        session().delete(user);
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

    public Session session(){
        return sessionFactory.getCurrentSession();
    }
}
