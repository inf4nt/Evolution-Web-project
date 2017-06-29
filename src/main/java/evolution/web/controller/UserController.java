package evolution.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import evolution.common.UserRoleEnum;
import evolution.dao.FriendsDao;
import evolution.model.friend.Friends;
import evolution.model.user.User;
import evolution.repository.UserRepository;
import evolution.service.MyJacksonService;
import evolution.service.SearchService;
import evolution.service.security.UserDetailsServiceImpl;
import evolution.service.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Created by Admin on 05.03.2017.
 */
@Controller
@RequestMapping ("/user")
@SessionAttributes(value = {"user"})
public class UserController {

    @Autowired
    private MyJacksonService jacksonService;
    @Autowired
    private FriendsDao friendsDao;
    @Autowired
    private Validator validator;
    @Autowired
    private SearchService searchService;
    @Autowired
    private UserRepository userRepository;

    private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @RequestMapping (value = "/id{id}", method = RequestMethod.GET)
    public String home (
            @PathVariable Long id,
            Model model,
            @SessionAttribute(required = false) User authUser) {

        if (authUser.getId().equals(id)) {
            model.addAttribute("user", authUser);
            LOGGER.info("session user\n" + authUser);
            LOGGER.info("My home. User id = " + id);
        } else {
            try {
                Friends friends = friendsDao.findUserAndFriendStatus(authUser.getId(), id);
                model.addAttribute("user", friends.getUser());
                LOGGER.info("session user\n" + friends.getUser());
                model.addAttribute("status", friends.getStatus());
                LOGGER.info("Other user id = " + friends.getUser().getId());
            } catch (NoResultException e) {
                LOGGER.error("User by id " + id +", is not exist\n" + e);
                return "redirect:/user/id" + authUser.getId();
            }
        }

        return "user/my-home";
    }

    @ResponseBody
    @GetMapping(value = "/", produces={"application/json; charset=UTF-8"})
    public List allUser(@RequestParam(required = false) Integer page,
                        @RequestParam(required = false) Integer size) throws JsonProcessingException {
        LOGGER.info("page=" + page + " size=" + size);
        if (size == null || page == null) {
            return userRepository.findUsers();
        }
        return userRepository.findUsers(new PageRequest(page, size));
    }

    // EDIT
    @ResponseBody
    @PutMapping(value = "/{id}", produces={"application/json; charset=UTF-8"})
    public boolean edit(@RequestBody String json,
                        @PathVariable Long id,
                        @SessionAttribute User user,
                        @AuthenticationPrincipal UserDetailsServiceImpl.CustomUser customUser,
                        HttpServletRequest request) throws IOException {

        User userRequest = (User) jacksonService.jsonToObject(json, User.class);
        // self update
        if (customUser.getUser().getId().equals(id)) {
            userRequest.setId(customUser.getUser().getId());
            userRequest.setLogin(customUser.getUser().getLogin());
            userRequest.setRegistrationDate(customUser.getUser().getRegistrationDate());
            userRequest.setRoleId(customUser.getUser().getRoleId());
        }
        //other update
        else if (request.isUserInRole("ROLE_ADMIN")) {
            userRequest.setId(user.getId());
            userRequest.setRegistrationDate(user.getRegistrationDate());

            // in future change login
            userRequest.setLogin(user.getLogin());
        }

        if (validator.userValidator(userRequest)) {
//            userDao.repository().update(userRequest);
            userRepository.save(userRequest);
            if (customUser.getUser().getId().equals(id))
                customUser.getUser().updateFields(userRequest);
            return true;
        }

        return false;
    }

    @ResponseBody
    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public User postUser(@RequestBody String json) throws IOException {
        User user = (User) jacksonService.jsonToObject(json, User.class);
        user.setRoleId(UserRoleEnum.USER.getId());
        if (validator.userValidator(user)) {
            return userRepository.save(user);
        }
        return null;
    }

    // DELETE
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseBody
    @DeleteMapping(value = "/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.delete(id);
    }

    // GET FORM PROFILE
    @RequestMapping(value = "/profile/{id}", method = RequestMethod.GET)
    public String profile(@PathVariable Long id,
                          @AuthenticationPrincipal UserDetailsServiceImpl.CustomUser customUser,
                          HttpServletRequest request,
                          Model model) {

        LOGGER.info("session user\n" + request.getSession().getAttribute("user"));


        if (id.equals(customUser.getUser().getId())) {
            model.addAttribute("user", customUser.getUser());
            return "user/form-my-profile";
        } else if (request.isUserInRole("ROLE_ADMIN")) {
            model.addAttribute("user", userRepository.findOne(id));
            return "admin/admin-form-profile";
        }

        return "redirect:/user/id" + customUser.getUser().getId();
    }

    @GetMapping(value = "/search")
    public String viewSearch(Model model, SessionStatus sessionStatus){
        sessionStatus.setComplete();
        LOGGER.info("session status set complete");
        int size = 5;
        model.addAttribute("limit", size);
        model.addAttribute("list", userRepository.findUsers(new PageRequest(0, size)));
        return "user/new-search";
    }

    @ResponseBody
    @GetMapping(value = "/search-result",
            produces={"application/json; charset=UTF-8"})
    public List resultSearch(@RequestParam(required = false, defaultValue = "") String like,
                             @RequestParam(required = false, defaultValue = "0") Integer size,
                             @RequestParam(required = false, defaultValue = "0") Integer page) throws JsonProcessingException {
        try {
            return searchService.searchUser(like, page, size);
        } catch (NoResultException e) {
            return null;
        }
    }
}
