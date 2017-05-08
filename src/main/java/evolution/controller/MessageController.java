package evolution.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import evolution.dao.MessageDao;
import evolution.dao.UserDao;
import evolution.model.Dialog;
import evolution.model.Message;
import evolution.model.User;
import evolution.service.MyJacksonService;
import evolution.service.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;
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
        List list = messageDao.findMyDialog(customUser.getId());
        model.addAttribute("list", list);
        return "message/form-dialog";
    }

    @RequestMapping(value = "/dialog", method = RequestMethod.GET)
    public String dialog(
            @AuthenticationPrincipal UserDetailsServiceImpl.CustomUser customUser,
            @RequestParam Long sel,
            Model model) {
        List<Message> list;
        if (messageDao.checkDialog(customUser.getId(), sel)) {
            list = messageDao.findMessageByUserId(customUser.getId(), sel);
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

    @ResponseBody @RequestMapping(value = "/getMessage", method = RequestMethod.GET)
    public String getMessage(
            @AuthenticationPrincipal UserDetailsServiceImpl.CustomUser customUser,
            @RequestParam Long sel
    ) throws JsonProcessingException {
        List result = messageDao.findMessageByUserId(customUser.getId(), sel);
        return jacksonService.objectToJson(result);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void saveMessage(
            @RequestParam String message,
            @SessionAttribute Long dialogId,
            @SessionAttribute Long sel,
            @AuthenticationPrincipal UserDetailsServiceImpl.CustomUser customUser,
            HttpServletResponse response) throws IOException {
        if (dialogId == -1) {
            // создать диалог, после перезагрузить страницу
            long nextId = messageDao.saveDialog(customUser.getId(), sel);
            messageDao.saveMessage(nextId, message, customUser.getId());
            response.sendRedirect("/im/dialog?sel=" + sel);
        } else {
            messageDao.saveMessage(dialogId, message, customUser.getId());
        }
    }

    @Autowired
    private MyJacksonService jacksonService;
    @Autowired
    private MessageDao messageDao;
    @Autowired
    private UserDao userDao;
}












//    @ResponseBody @RequestMapping(value = "/", method = RequestMethod.GET)
//    public String im(
//            @AuthenticationPrincipal UserDetailsServiceImpl.CustomUser customUser,
//            SessionStatus sessionStatus) throws JsonProcessingException {
//        sessionStatus.setComplete();
//        List list = messageDao.findMyDialog(customUser.getId());
//        return jacksonService.objectToJson(list);
//    }
//
//    @ResponseBody @RequestMapping(value = "/dialog", method = RequestMethod.GET)
//    public String dialog(
//            @AuthenticationPrincipal UserDetailsServiceImpl.CustomUser customUser,
//            @RequestParam Long sel,
//            Model model
//    ) throws JsonProcessingException {
//        List<Message> result = null;
//
//        result = messageDao.findMessageByUserId(customUser.getId(), sel);
//
//        model.addAttribute("dialogId", result.get(0).getDialog().getId());
//        model.addAttribute("sel", sel);
//        return jacksonService.objectToJson(result);
//    }
//
//
//    @ResponseStatus(HttpStatus.OK) @RequestMapping (value = "/write", method = RequestMethod.POST)
//    public void write (
//            @AuthenticationPrincipal UserDetailsServiceImpl.CustomUser customUser,
//            @RequestParam String message,
//            @SessionAttribute Long dialogId,
//            @SessionAttribute Long sel
//    ) {
//        messageDao.saveMessage(dialogId, message, sel);
//    }