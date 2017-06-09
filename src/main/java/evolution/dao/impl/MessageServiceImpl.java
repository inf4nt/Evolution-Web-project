package evolution.dao.impl;

import evolution.dao.DialogDao;
import evolution.dao.MessageService;
import evolution.model.dialog.Dialog;
import evolution.model.message.Message;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.List;

/**
 * Created by Admin on 09.06.2017.
 */
@Repository
@Transactional
public class MessageServiceImpl
                        implements MessageService {

    private static final String CHECK_DIALOG_BY_USER_ID = " select 1 from Dialog d " +
            " where (d.first.id =:id1 and d.second.id =:id2) " +
            " or (d.first.id =:id2 and d.second.id =:id1) ";

    private static final String FIND_MESSAGE = " select new Message (m.id, m.message, m.dateDispatch, " +
            " sender.id, sender.firstName, sender.lastName, d.id) " +
            " from Message m " +
            " join m.dialog as d " +
            " join m.sender as sender " +
            " where (d.first.id =:id1 and d.second.id =:id2 ) " +
            " or (d.first.id =:id2 and d.second.id =:id1 ) order by m.id desc";

    private static final String FIND_LAST_MESSAGES_FOR_DIALOG = "select new Message (d.id, m.id, substring(m.message, 0, 40), m.dateDispatch," +
            " sender.id, sender.firstName, sender.lastName, " +
            " second.id, second.firstName, second.lastName) " +
            " from Message m " +
            " join m.sender as sender " +
            " join m.dialog as d" +
            " join User second on ( " +
            "       (second.id = d.first.id and d.first.id !=:id1 ) " +
            "       or (second.id = d.second.id and d.second.id !=:id1 ) " +
            " ) " +
            " where m.id in (" +
            " select max (m.id) " +
            " from Message m " +
            " join m.dialog as d " +
            " where d.first.id =:id1 " +
            " or d.second.id =:id1 " +
            " group by m.dialog.id ) " +
            " order by m.id desc ";

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageServiceImpl.class);
    private SessionFactory sessionFactory;
    @Autowired
    private DialogDao dialogDao;

    @Autowired
    public MessageServiceImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Long save(Dialog dialog) {
        return dialogDao.save(dialog);
    }

    @Override
    public void update(Dialog dialog) {
        dialogDao.update(dialog);
    }

    @Override
    public void delete(Dialog dialog) {
        dialogDao.delete(dialog);
    }

    @Override
    public Dialog find(Dialog dialog) {
        return dialogDao.find(dialog);
    }

    @Override
    public void save(Message message) {
        session().save(message);
    }

    @Override
    public void merge(Message message) {
        session().merge(message);
    }

    @Override
    public void update(Message message) {
        session().update(message);
    }

    @Override
    public void delete(Message message) {
        session().delete(message);
    }

    @Override
    public Message find(Message message) {
        return session().find(message.getClass(), message.getId());
    }

    @Override
    public boolean checkDialog(long authUserId, long second) {
        LOGGER.info("Run check dialog. Users by id " + authUserId + " and " + second);
        Query query = session().createQuery(CHECK_DIALOG_BY_USER_ID);
        query.setParameter("id1", authUserId);
        query.setParameter("id2", second);
        try {
            query.getSingleResult();
            LOGGER.info("Users by id " + authUserId + " and " + second + ", have dialog");
            return true;
        } catch (NoResultException nre){
            LOGGER.info("Users by id " + authUserId + " and " + second + ", not have dialog\n" + nre.toString());
            return false;
        }
    }

    @Override
    public List<Message> findMessage(Long authUserId, Long second, int limit, int offset) {
        Query query = session().createQuery(FIND_MESSAGE);
        query.setParameter("id1", authUserId);
        query.setParameter("id2", second);
        query.setMaxResults(limit);
        query.setFirstResult(offset);
        return query.list();
    }

    @Override
    public List<Message> findLastMessageForDialog(Long authUserId, int limit, int offset) {
        Query query = session().createQuery(FIND_LAST_MESSAGES_FOR_DIALOG);
        query.setParameter("id1", authUserId);
        query.setMaxResults(limit);
        query.setFirstResult(offset);
        return query.list();
    }

    public Session session(){
        return sessionFactory.getCurrentSession();
    }
}
