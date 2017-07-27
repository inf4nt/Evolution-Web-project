package evolution.dao;

import evolution.model.dialog.Dialog;
import evolution.model.message.Message;
import evolution.model.user.StandardUser;
import evolution.model.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


/**
 * Created by Infant on 13.07.2017.
 */
@Service
public class MessageDaoService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DialogRepository dialogRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Transactional
    public boolean existDialog(Long userId1, Long userId2) {
        return dialogRepository.checkDialog(userId1, userId2) != null;
    }

    @Transactional
    public List<Message> findMessage(Long userId1, Long userId2, Pageable pageable) {
        return messageRepository.findMessage(userId1, userId2, pageable);
    }

    @Transactional
    public List<Message> findMessageIgnoreDialog(Long userId1, Long userId2, Pageable pageable) {
        LOGGER.warn("Message not have dialog, maybe catch null pointer exception");
        return messageRepository.findMessageIgnoreDialog(userId1, userId2, pageable);
    }

    @Transactional
    public List<Message> findLastMessageForDialog(Long userId) {
        return messageRepository.findLastMessageForDialog(userId);
    }

    @Transactional
    public Message saveDialogAndMessage(Dialog dialog, Message message) {
        Dialog d = dialogRepository.save(dialog);
        message.setDialog(d);
        return messageRepository.save(message);
    }

    @Transactional
    public Message saveDialogAndMessage(Long senderUserId, Long secondUserId, String message, Date dateMessage) {
        Dialog dialog = new Dialog(new User(senderUserId), new User(secondUserId));
        dialogRepository.save(dialog);
        Message m = new Message(new User(senderUserId), message, dateMessage);
        m.setDialog(dialog);
        return messageRepository.save(m);
    }

    @Transactional
    public Message save(Message message) {
        return messageRepository.save(message);
    }

    @Transactional
    public Dialog findDialogByFirstAndSecond(@Param("first") Long firstUserId, @Param("second") Long secondUserId){
        return dialogRepository.findDialogByFirstAndSecond(firstUserId, secondUserId);
    }

    @Transactional
    public Dialog selectDialogIdByFirstAndSecond(@Param("first") Long firstUserId, @Param("second") Long secondUserId){
        return dialogRepository.selectDialogIdByFirstAndSecond(firstUserId, secondUserId);
    }

}
