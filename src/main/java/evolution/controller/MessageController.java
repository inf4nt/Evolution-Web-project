package evolution.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import evolution.dao.MessageDao;
import evolution.dao.UserDao;
import evolution.model.message.Message;
import evolution.service.MyJacksonService;
import evolution.service.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
        if (dialogId == -1) {
            long nextId = messageDao.saveDialog(customUser.getUser().getId(), sel);
            messageDao.saveMessage(nextId, message, customUser.getUser().getId());
            response.sendRedirect("/im/" + sel);
        } else {
            messageDao.saveMessage(dialogId, message, customUser.getUser().getId());
        }
    }

    @ResponseBody @RequestMapping(value = "/getMessage", method = RequestMethod.GET, produces={"application/json; charset=UTF-8"})
    public String getMessage(
            @AuthenticationPrincipal UserDetailsServiceImpl.CustomUser customUser,
            @RequestParam Long sel
    ) throws JsonProcessingException {
        List result = messageDao.findMessageByUserId(customUser.getUser().getId(), sel, 7, 0);
        return jacksonService.objectToJson(result);
    }


    @Autowired
    private MyJacksonService jacksonService;
    @Autowired
    private MessageDao messageDao;
    @Autowired
    private UserDao userDao;

}


//    @RequestMapping(value = "/im", method = RequestMethod.GET)
//    public String imLast(@AuthenticationPrincipal UserDetailsServiceImpl.CustomUser customUser,
//                         Model model) {
//        List<Message> list = messageDao.lastMessagesFromDialog (customUser.getUser().getId());
//        model.addAttribute("list", list);
//        return "message/dialog-message";
//    }
//
//    @ResponseBody @RequestMapping(value = "/im/{authUserId}/{userId}", produces={"application/json; charset=UTF-8"}, method = RequestMethod.GET)
//    public String messageFromDialog(@AuthenticationPrincipal UserDetailsServiceImpl.CustomUser customUser,
//                                    HttpServletRequest request,
//                                    @PathVariable Long authUserId,
//                                    @PathVariable Long userId) throws JsonProcessingException {
//        if (request.isUserInRole("ROLE_ADMIN") || customUser.getUser().getId().equals(authUserId)) {
//            List result = messageDao.findMessageByUserId(authUserId, userId);
//            return jacksonService.objectToJson(result);
//        } else {
//            return null;
//        }
//    }










