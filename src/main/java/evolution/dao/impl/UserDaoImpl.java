package evolution.dao.impl;


import evolution.common.UserRoleEnum;
import evolution.dao.UserDao;
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
    public void update(User userEvolution) {
        hibernateSession = sessionFactory.getCurrentSession();
        hibernateSession.update(userEvolution);
    }

    @Override
    public List<User> findAll() {
        hibernateSession = sessionFactory.getCurrentSession();
        List<User> result = hibernateSession.createQuery("from User").list();
        return result;
    }

    @Override
    public User findById(long id) {
        hibernateSession = sessionFactory.getCurrentSession();
        User result = hibernateSession.find(User.class, id);
        return result;
    }

    @Override
    public User findByLogin(String login) {
        hibernateSession = sessionFactory.getCurrentSession();
        Query query = hibernateSession.createQuery("" +
                "from User " +
                "where login=:l");
        query.setParameter("l", login);
        return (User) query.getSingleResult();
    }

    @Override
    public void deleteById(long id) {
        hibernateSession = sessionFactory.getCurrentSession();
        Query query = hibernateSession.createQuery("" +
                "delete " +
                "from User " +
                "where id=:i");
        query.setParameter("i", id);
        query.executeUpdate();
    }

    @Override
    public void delete(User userEvolution) {
        hibernateSession = sessionFactory.getCurrentSession();
        hibernateSession.delete(userEvolution);
    }

    @Override
    public List<User> findLikeLogin(String like) {
        hibernateSession = sessionFactory.getCurrentSession();
        Query query = hibernateSession.createQuery(
                "select new User (id, login, firstName, lastName) " +
                        "from User " +
                        "where login like:l");
        query.setParameter("l", like);
        List<User> result = query.list();
        return result;
    }

    @Override
    public List<User> findAllUser() {
        hibernateSession = sessionFactory.getCurrentSession();
        Query query = hibernateSession.createQuery(
                "select new User(id, login, firstName, lastName) " +
                        "from User " +
                        "where roleId = " + UserRoleEnum.USER.getId());
        List result = query.list();
        return result;
    }

    @Override
    public List<User> findAllAdmin() {
        hibernateSession = sessionFactory.getCurrentSession();
        Query query = hibernateSession.createQuery(
                "select new User(id, login, firstName, lastName) " +
                        "from User " +
                        "where roleId = " + UserRoleEnum.ADMIN.getId());
        List result = query.list();
        return result;
    }

    @Override
    public List<User> findAdminLikeLogin(String like) {
        hibernateSession = sessionFactory.getCurrentSession();
        Query query = hibernateSession.createQuery(
                "select new User (id, login, firstName, lastName) " +
                        "from User " +
                        "where login like:l " +
                        "and roleId = " + UserRoleEnum.ADMIN.getId());
        query.setParameter("l", like);
        List result = query.list();
        return result;
    }

    @Override
    public List<User> findUserLikeLogin(String like) {
        hibernateSession = sessionFactory.getCurrentSession();
        Query query = hibernateSession.createQuery(
                "select new User(id, firstName, lastName) " +
                        "from User " +
                        "where login like:l " +
                        "and roleId = " + UserRoleEnum.USER.getId());
        query.setParameter("l", like);
        List result = query.list();
        return result;
    }

    @Override
    public List<User> findUserLikeFirstNameLastName(String parameter1, String parameter2) {
        hibernateSession = sessionFactory.getCurrentSession();
        Query query = hibernateSession.createQuery(
                "select new User(id, first_name, last_name) " +
                "from USER_DATA " +
                "WHERE (FIRST_NAME LIKE :p1 and LAST_NAME LIKE :p2) " +
                "or (LAST_NAME LIKE :p1 and FIRST_NAME LIKE :p2 and ROLE_ID =  " + UserRoleEnum.USER.getId());
        query.setParameter("p1", parameter1);
        query.setParameter("p2", parameter2);
        List result = query.list();
        return result;
    }


    public static class UserMapper {

        public User mapper (Object a){
            User user = new User();
            Object rows[] = (Object[]) a;
            user.setId(Long.parseLong(rows[0].toString()));
            user.setFirstName(rows[1].toString());
            user.setLastName(rows[2].toString());
            return user;
        }

        public List<User> listUser (List list) {
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
