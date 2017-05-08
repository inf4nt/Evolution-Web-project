package evolution.dao.impl;

import evolution.dao.MessageDao;
import evolution.model.Dialog;
import evolution.model.Message;
import evolution.model.User;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * Created by Admin on 18.04.2017.
 */
@Repository
@Transactional
@NoArgsConstructor
public class MessageDaoImpl
        implements MessageDao {

    public MessageDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Dialog> findMyDialog(long userid) {
        hibernateSession = sessionFactory.getCurrentSession();
        Query query = hibernateSession.createQuery(FIND_MY_DIALOG);
        query.setParameter("id", userid);
        return query.list();
    }

    @Override
    public Long saveDialog(long firstId, long secondId) {
        hibernateSession = sessionFactory.getCurrentSession();
        long nextval = ((BigInteger) hibernateSession.createNativeQuery(MessageDao.NEXT_VAL_FROM_DIALOG).uniqueResult()).longValue();
        User first = new User(firstId);
        User second = new User(secondId);
        Dialog d1 = new Dialog(nextval, first, second);
        Dialog d2 = new Dialog(nextval, second, first);
        hibernateSession.save(d1);
        hibernateSession.save(d2);
        return nextval;
    }

    @Override
    public void deleteDialog(long dialogId) {
        hibernateSession = sessionFactory.getCurrentSession();
        Query query = hibernateSession.createQuery(DELETE_DIALOG);
        query.setParameter("id", dialogId);
        query.executeUpdate();
    }

    @Override
    public List<Message> findMessageByDialogAndAuthUserId(long dialogId, long authUserId) {
        hibernateSession = sessionFactory.getCurrentSession();
        Query queryCount = hibernateSession.createQuery(COUNT_MESSAGE);
        queryCount.setParameter("dialog", dialogId);
        int count = Math.toIntExact((Long) queryCount.uniqueResult());
        Query query = hibernateSession.createQuery(FIND_MY_MESSAGE_BY_DIALOG_AND_AUTH_USER_ID);
        if (count > 10) {
            query.setFirstResult(count - 10);
        }
        query.setMaxResults(10);

        query.setParameter("authUser", authUserId);
        query.setParameter("dialog", dialogId);
        return query.list();
    }

    @Override
    public List<Message> findMessageByUserId(long authUserId, long second) {
        hibernateSession = sessionFactory.getCurrentSession();
        Query query = hibernateSession.createQuery(FIND_MY_MESSAGE_BY_USER_ID);
        query.setMaxResults(7);
        query.setParameter("authUser", authUserId);
        query.setParameter("second", second);
        return query.list();
    }

    @Override
    public void saveMessage(long dialogId, String message, Long senderId) {
        hibernateSession = sessionFactory.getCurrentSession();
        Message m = new Message(dialogId, new User(senderId), message, new Date());
        hibernateSession.save(m);
    }

    @Override
    public boolean checkDialog(long authUserId, long second) {
        hibernateSession = sessionFactory.getCurrentSession();
        Query query = hibernateSession.createQuery(CHECK_DIALOG_BY_USER_ID);
        query.setParameter("first", authUserId);
        query.setParameter("second", second);
        try {
            query.getSingleResult();
            return true;
        } catch (NoResultException nre){
            return false;
        }
    }


    private Session hibernateSession;
    @Autowired
    private SessionFactory sessionFactory;
}
