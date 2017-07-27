package evolution.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import evolution.dao.MessageDaoService;
import evolution.dao.UserDaoService;
import evolution.model.dialog.Dialog;
import evolution.model.message.Message;
import evolution.service.MyJacksonService;
import evolution.service.security.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by Admin on 19.04.2017.
 */

@Controller
@RequestMapping("/im")
@SessionAttributes({"sel"})
public class MessageController {

    @Autowired
    private MessageDaoService messageDaoService;

    @Autowired
    private UserDaoService userDaoServiceImpl;

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String im(
            @AuthenticationPrincipal UserDetailsServiceImpl.CustomUser customUser,
            Model model,
            SessionStatus sessionStatus) {

        sessionStatus.setComplete();
        List<Message> list = messageDaoService.findLastMessageForDialog(customUser.getUser().getId());
        model.addAttribute("list", list);
        return "message/form-dialog";
    }

    @RequestMapping(value = "/{sel}", method = RequestMethod.GET)
    public String dialog(@AuthenticationPrincipal UserDetailsServiceImpl.CustomUser  customUser,
                         @PathVariable Long sel,
                         Model model) {

        List<Message> list = messageDaoService.findMessage(customUser.getUser().getId(), sel, new PageRequest(0, 7));
        model.addAttribute("list", list);
        model.addAttribute("sel", sel);
        model.addAttribute("im", userDaoServiceImpl.selectIdFirstLastNameStandardUser(sel));
        return "message/form-message";
    }

    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void saveMessage(
            @RequestParam String message,
            @RequestParam Long sel,
            @AuthenticationPrincipal UserDetailsServiceImpl.CustomUser customUser,
            HttpServletResponse response) throws IOException {

        Dialog dialog = messageDaoService.selectDialogIdByFirstAndSecond(customUser.getUser().getId(), sel);
        if (dialog != null) {
            LOGGER.info("Dialog exist. Run save message");
            messageDaoService.save(new Message(customUser.getUser().getId(), message, new Date(), dialog.getId()));
        } else {
            LOGGER.info("create new dialog");
            messageDaoService.saveDialogAndMessage(customUser.getUser().getId(), sel, message, new Date());
            response.sendRedirect("/im/" + sel);
        }
    }

    @ResponseBody
    @GetMapping(value = "/getMessage",
            produces={"application/json; charset=UTF-8"})
    public List<Message> getMessage(
            @AuthenticationPrincipal UserDetailsServiceImpl.CustomUser customUser,
            @RequestParam Long sel) throws JsonProcessingException {
        LOGGER.info("interval message");
        return messageDaoService.findMessageIgnoreDialog(customUser.getUser().getId(), sel, new PageRequest(0, 7));
    }
}
