package evolution.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import evolution.dao.MessageDao;
import evolution.dao.UserDao;
import evolution.model.message.Message;
import evolution.model.user.User;
import evolution.service.MyJacksonService;
import evolution.service.security.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by Admin on 19.04.2017.
 */

@Controller
@RequestMapping("/im")
@SessionAttributes({"dialogId", "sel"})
public class MessageController {


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String im(
            @AuthenticationPrincipal UserDetailsServiceImpl.CustomUser customUser,
            Model model,
            SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        List<Message> list = messageDao.lastMessagesFromDialog (customUser.getUser().getId());
        model.addAttribute("list", list);
        return "message/form-dialog";
    }

    @RequestMapping(value = "/{sel}", method = RequestMethod.GET)
    public String dialog(@AuthenticationPrincipal UserDetailsServiceImpl.CustomUser  customUser,
                         @PathVariable Long sel,
                         Model model) {
        List<Message> list;

        if (messageDao.checkDialog(customUser.getUser().getId(), sel)) {
            list = messageDao.findMessageByUserId(customUser.getUser().getId(), sel, 7, 0);
        } else {
            model.addAttribute("im", userDao.selectIdFirstLastName(sel));
            model.addAttribute("dialogId", -1l);
            model.addAttribute("sel", sel);
            return "message/form-message";
        }

        model.addAttribute("list", list);
        model.addAttribute("dialogId", list.get(0).getDialog().getId());
        model.addAttribute("sel", sel);
        return "message/form-message";
    }

    @ResponseBody @RequestMapping(value = "/", method = RequestMethod.POST)
    public void saveMessage(
            @RequestParam String message,
            @SessionAttribute Long dialogId,
            @SessionAttribute Long sel,
            @AuthenticationPrincipal UserDetailsServiceImpl.CustomUser customUser,
            HttpServletResponse response) throws IOException {
        Message m;
        if (dialogId == -1) {
            long nextId = messageDao.saveDialog(customUser.getUser().getId(), sel);
            logger.info("create new dialog");
            m = new Message(customUser.getUser(), message, new Date(), new Message.MessageDialog(nextId));
            messageDao.save(m);
            response.sendRedirect("/im/" + sel);
        } else {
            m = new Message(customUser.getUser(), message, new Date(), new Message.MessageDialog(dialogId));
            messageDao.save(m);
        }
        logger.info("save message | " + m);
    }

    @ResponseBody @RequestMapping(value = "/getMessage", method = RequestMethod.GET, produces={"application/json; charset=UTF-8"})
    public String getMessage(
            @AuthenticationPrincipal UserDetailsServiceImpl.CustomUser customUser,
            @RequestParam Long sel
    ) throws JsonProcessingException {
        List result = messageDao.findMessageByUserId(customUser.getUser().getId(), sel, 7, 0);
        logger.info("interval message");
        return jacksonService.objectToJson(result);
    }


    @Autowired
    private MyJacksonService jacksonService;
    @Autowired
    private MessageDao messageDao;
    @Autowired
    private UserDao userDao;
    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);
}
