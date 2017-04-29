package evolution.controller;

import evolution.dao.MessageDao;
import evolution.dao.UserDao;
import evolution.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Admin on 19.04.2017.
 */
@Controller
@RequestMapping("/im")
public class MessageController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String im(HttpServletRequest request, Model model) {
        long authUserId = ((User) request.getSession().getAttribute("authUser")).getId();
        List list = messageDao.findMyDialog(authUserId);
        model.addAttribute("list", list);
        return "message/form-dialog";
    }

    @RequestMapping(value = "/dialog", method = RequestMethod.GET)
    public String dialog(HttpServletRequest request, Model model) {
        long authUserId = ((User) request.getSession().getAttribute("authUser")).getId();
        long secondId = Long.parseLong(request.getParameter("sel"));

        try {
            request.getParameter("media").isEmpty();
            if (messageDao.checkDialog(authUserId, secondId)) {
                List list = messageDao.findMessageByUserId(authUserId, secondId);
                model.addAttribute("list", list);
            } else {
                model.addAttribute("im", userDao.selectIdFirstLastName(secondId));
                model.addAttribute("dialogId", "new");
            }
        } catch (NullPointerException npe){
            List list = messageDao.findMessageByUserId(authUserId, secondId);
            model.addAttribute("list", list);
        }

        return "message/form-message";
    }

    @RequestMapping (value = "/write", method = RequestMethod.POST)
    public String write (HttpServletRequest request) {
        long authUserId = ((User) request.getSession().getAttribute("authUser")).getId();
        long second = Long.parseLong(request.getParameter("sel"));
        String message = request.getParameter("message");
        long dialogId;
        try {
            request.getParameter("media").isEmpty();
            if (message.isEmpty())
                return "redirect:/im/dialog?sel=" + second;
            else
                dialogId = messageDao.saveDialog(authUserId, second);
        } catch (NullPointerException npe){
            dialogId = Long.parseLong(request.getParameter("dialogId"));
        }

        messageDao.saveMessage(dialogId, message, authUserId);
        return "redirect:/im/dialog?sel=" + second;
    }

    @Autowired
    private MessageDao messageDao;
    @Autowired
    private UserDao userDao;
}
