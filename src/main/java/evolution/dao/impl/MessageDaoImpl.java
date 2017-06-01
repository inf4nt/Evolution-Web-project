package evolution.dao.impl;

import evolution.dao.MessageDao;
import evolution.model.message.Dialog;
import evolution.model.message.Message;
import evolution.model.user.User;
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
        Query query = session().createQuery(FIND_MY_DIALOG);
        query.setParameter("id", userid);
        return query.list();
    }

    @Override
    public Long saveDialog(long firstId, long secondId) {
        long nextval = ((BigInteger) session().createNativeQuery(MessageDao.NEXT_VAL_FROM_DIALOG).uniqueResult()).longValue();
        User first = new User(firstId);
        User second = new User(secondId);
        Dialog d1 = new Dialog(nextval, first, second);
        Dialog d2 = new Dialog(nextval, second, first);
        session().save(d1);
        session().save(d2);
        return nextval;
    }

    @Override
    public void deleteDialog(long dialogId) {
        Query query = session().createQuery(DELETE_DIALOG);
        query.setParameter("id", dialogId);
        query.executeUpdate();
    }

    @Override
    public List<Message> findMessageByDialogAndAuthUserId(long dialogId, long authUserId) {
        Query queryCount = session().createQuery(COUNT_MESSAGE);
        queryCount.setParameter("dialog", dialogId);
        int count = Math.toIntExact((Long) queryCount.uniqueResult());
        Query query = session().createQuery(FIND_MY_MESSAGE_BY_DIALOG_AND_AUTH_USER_ID);
        if (count > 10) {
            query.setFirstResult(count - 10);
        }
        query.setMaxResults(10);

        query.setParameter("authUser", authUserId);
        query.setParameter("dialog", dialogId);
        return query.list();
    }

    @Override
    public List<Message> findMessageByUserId(long authUserId, long second, int limit, int offset) {
        Query query = session().createQuery(FIND_MY_MESSAGE_BY_USER_ID);
        query.setMaxResults(limit);
        query.setFirstResult(offset);
        query.setParameter("authUser", authUserId);
        query.setParameter("second", second);
        return query.list();
    }

    @Override
    public void saveMessage(long dialogId, String message, Long senderId) {
        Message m = new Message(dialogId, new User(senderId), message, new Date());
        session().save(m);
    }

    @Override
    public void save(Message message) {
        session().save(message);
    }

    @Override
    public boolean checkDialog(long authUserId, long second) {
        Query query = session().createQuery(CHECK_DIALOG_BY_USER_ID);
        query.setParameter("first", authUserId);
        query.setParameter("second", second);
        try {
            query.getSingleResult();
            return true;
        } catch (NoResultException nre){
            return false;
        }
    }

    @Override
    public List<Message> lastMessagesFromDialog (long authUserId) {
        Query query = session().createQuery(LAST_MESSAGES);
        query.setParameter("id", authUserId);
        return query.list();
    }

    public Session session() {
        return sessionFactory.getCurrentSession();
    }

    @Autowired
    private SessionFactory sessionFactory;
}
