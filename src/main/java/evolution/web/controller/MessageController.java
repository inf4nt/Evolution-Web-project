package evolution.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import evolution.model.dialog.Dialog;
import evolution.model.message.Message;
import evolution.model.user.User;
import evolution.repository.DialogRepository;
import evolution.repository.MessageRepository;
import evolution.repository.UserRepository;
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
@SessionAttributes({"dialogId", "sel"})
public class MessageController {

    @Autowired
    private MyJacksonService jacksonService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private DialogRepository dialogRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String im(
            @AuthenticationPrincipal UserDetailsServiceImpl.CustomUser customUser,
            Model model,
            SessionStatus sessionStatus) {

        sessionStatus.setComplete();
        List list = messageRepository.findLastMessageForDialog(customUser.getUser().getId());
        model.addAttribute("list", list);
        return "message/form-dialog";
    }

    @RequestMapping(value = "/{sel}", method = RequestMethod.GET)
    public String dialog(@AuthenticationPrincipal UserDetailsServiceImpl.CustomUser  customUser,
                         @PathVariable Long sel,
                         Model model) {

        if (dialogRepository.checkDialog(customUser.getUser().getId(), sel) != null) {
            List<Message> list = messageRepository.findMessage(customUser.getUser().getId(), sel, new PageRequest(0, 7));

            Long dialogId = list.get(0).getDialog().getId();
            model.addAttribute("dialogId", dialogId);
            model.addAttribute("list", list);
            LOGGER.info("dialog id  = " + dialogId);
            LOGGER.info("dialog with user by id " + sel + " is EXIST");

        } else {
            LOGGER.info("dialog with user by id " + sel + " is not exist");
            model.addAttribute("dialogId", -1);
            LOGGER.info("session add attribute dialogId = -1");
        }

        model.addAttribute("sel", sel);
        model.addAttribute("im", userRepository.selectIdFirstLastName(sel));
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

        LOGGER.info("dialog id = " + dialogId);

        if (dialogId == -1) {
            LOGGER.info("create new dialog");
            Dialog dialog = new Dialog(customUser.getUser(), new User(sel));
            LOGGER.info(dialog.toString());

            Dialog d = dialogRepository.saveAndFlush(dialog);

            LOGGER.info("dialog id= " + d.getId());
            m = new Message(d.getId(), customUser.getUser(), message, new Date());
            messageRepository.save(m);
            response.sendRedirect("/im/" + sel);
        } else {
            LOGGER.info("Dialog exist. Run save message");
            m = new Message(customUser.getUser().getId(), message, new Date(), dialogId);
            messageRepository.save(m);
            LOGGER.info("Message saved\n" + m);
        }
    }

    @ResponseBody
    @GetMapping(value = "/getMessage",
            produces={"application/json; charset=UTF-8"})
    public String getMessage(
            @AuthenticationPrincipal UserDetailsServiceImpl.CustomUser customUser,
            @RequestParam Long sel) throws JsonProcessingException {
        LOGGER.info("interval message start");
        List result = messageRepository.findMessage(customUser.getUser().getId(), sel, new PageRequest(0, 7));
        LOGGER.info("interval message end");
        return jacksonService.objectToJson(result);
    }
}
