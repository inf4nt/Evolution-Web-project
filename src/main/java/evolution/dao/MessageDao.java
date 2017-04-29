package evolution.dao;

import evolution.model.Dialog;
import evolution.model.Message;

import java.util.List;

/**
 * Created by Admin on 18.04.2017.
 */
public interface MessageDao {

    List<Dialog> findMyDialog(long userid);

    Long saveDialog (long firstId, long secondId);

    void deleteDialog (long dialogId);

    List<Message> findMessageByDialogAndAuthUserId (long dialogId, long authUserId);

    List<Message> findMessageByUserId (long authUserId, long second);

    void saveMessage (long dialogId, String message, Long senderId);

    boolean checkDialog(long authUserId, long second);

    String FIND_MY_DIALOG = "select new Dialog (d.dialogPK.id, " +
            " first.id, first.firstName, first.lastName, " +
            " second.id, second.firstName, second.lastName) " +
            " from Dialog d " +
            " join fetch User second on second.id = d.dialogPK.second.id and d.dialogPK.first.id = :id " +
            " join fetch User first on first.id = d.dialogPK.first.id and first.id = :id ";

    String DELETE_DIALOG = "delete from Dialog where dialogPK.id = :id";

    String COUNT_MESSAGE = "select count(1) from Message where dialog.id = :dialog";

    String NEXT_VAL_FROM_DIALOG = "SELECT nextval('seq_dialog_id') as nextval";

    String FIND_MY_MESSAGE_BY_DIALOG_AND_AUTH_USER_ID = " select new Message ( d.id, " +
             " m.id, m.message, m.dateDispatch," +
             " sender.id, sender.firstName, sender.lastName," +
             " im.id, im.firstName, im.lastName )" +
             " from Message m " +
             " join m.sender as sender " +
             " join m.dialog as d " +
             " join d.second as im" +
             " where d.first.id = :authUser and d.id = :dialog " +
             " order by m.dateDispatch asc ";

    String FIND_MY_MESSAGE_BY_USER_ID =  " select new Message ( d.id , " +
            " m.id, m.message, m.dateDispatch," +
            " sender.id, sender.firstName, sender.lastName," +
            " im.id, im.firstName, im.lastName )" +
            " from Message m " +
            " join m.dialog as d " +
            " join m.sender as sender " +
            " join d.second as im " +
            " where d.first.id = :authUser and d.second.id = :second " +
            " order by m.dateDispatch asc ";

    String CHECK_DIALOG_BY_USER_ID =  " from Dialog d " +
            " where d.dialogPK.first.id = :first " +
            " and d.dialogPK.second.id = :second ";
}
