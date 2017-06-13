package evolution.dao.impl;


import evolution.dao.DialogDao;
import evolution.model.dialog.Dialog;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigInteger;

/**
 * Created by Admin on 08.06.2017.
 */
@Repository
@Transactional
public class DialogDaoImpl implements DialogDao {

    private static final String NEXT_VAL_FROM_DIALOG = "SELECT nextval('seq_dialog_id') as nextval";

    private SessionFactory sessionFactory;

    @Autowired
    private evolution.dao.Repository repository;

    @Autowired
    public DialogDaoImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public evolution.dao.Repository repository() {
        return repository;
    }

    @Override
    public Long save(Dialog dialog) {
        long nextval = ((BigInteger) session().createNativeQuery(NEXT_VAL_FROM_DIALOG).uniqueResult()).longValue();
        dialog.setId(nextval);
        session().save(dialog);
        return nextval;
    }

    @Override
    public Dialog find(Dialog dialog) {
        return session().find(dialog.getClass(), dialog.getId());
    }

    public Session session(){
        return sessionFactory.getCurrentSession();
    }
}
