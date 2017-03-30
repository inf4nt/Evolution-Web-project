package evolution.dao.impl;

import evolution.dao.SecretQuestionTypeDao;
import evolution.model.SecretQuestionType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by Admin on 09.03.2017.
 */
@Repository
@Transactional
public class SecretQuestionTypeDaoImpl implements SecretQuestionTypeDao {
    @Override
    public void save(SecretQuestionType secretQuestionType) {
        hibernateSession = sessionFactory.getCurrentSession();
        hibernateSession.save(secretQuestionType);
    }

    @Override
    public void update(SecretQuestionType secretQuestionType) {
        hibernateSession = sessionFactory.getCurrentSession();
        hibernateSession.update(secretQuestionType);
    }

    @Override
    public List<SecretQuestionType> findAll() {
        hibernateSession = sessionFactory.getCurrentSession();
        List<SecretQuestionType> result = hibernateSession.createQuery(FIND_ALL).list();
        return result;
    }

    @Override
    public SecretQuestionType findById(long id) {
        hibernateSession = sessionFactory.getCurrentSession();
        SecretQuestionType result = hibernateSession.find(SecretQuestionType.class, id);
        return result;
    }

    @Override
    public SecretQuestionType findByName(String name) {
        hibernateSession = sessionFactory.getCurrentSession();
        Query query = hibernateSession.createQuery("from SecretQuestionType where name =:n");
        query.setParameter("n", name);
        return (SecretQuestionType) query.getSingleResult();
    }

    @Override
    public void delete(SecretQuestionType secretQuestionType) {
        hibernateSession = sessionFactory.getCurrentSession();
        hibernateSession.delete(secretQuestionType);
    }

    @Override
    public void deleteById(long id) {
        hibernateSession = sessionFactory.getCurrentSession();
        Query query = hibernateSession.createQuery(DELETE_BY_ID);
        query.setParameter("i", id);
    }

    private Session hibernateSession;
    @Autowired
    private SessionFactory sessionFactory;
}
