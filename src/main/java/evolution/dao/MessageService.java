package evolution.dao;


import evolution.model.dialog.Dialog;
import evolution.model.message.Message;

import java.util.List;

/**
 * Created by Admin on 09.06.2017.
 */
public interface MessageService extends DefaultDao {

    Long save(Dialog dialog);

    Dialog find(Dialog dialog);

    Message find(Message message);

    boolean checkDialog(long authUserId, long second);

    List<Message> findMessage (Long authUserId, Long second, int limit, int offset);

    List<Message>  findLastMessageForDialog (Long authUserId, int limit, int offset);
}
