package evolution.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import evolution.common.FriendStatusEnum;
import evolution.common.UserRoleEnum;
import evolution.dao.FeedServiceDao;
import evolution.dao.FriendsDaoService;
import evolution.dao.UserDaoService;
import evolution.model.friend.Friends;
import evolution.model.user.StandardUser;
import evolution.model.user.User;
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
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

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
    private FriendsDaoService friendsDaoService;
    @Autowired
    private Validator validator;
    @Autowired
    private SearchService searchService;
    @Autowired
    private UserDaoService userDaoService;
    @Autowired
    private FeedServiceDao feedServiceDao;


    private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @RequestMapping (value = "/id{id}", method = RequestMethod.GET)
    public String home (@PathVariable Long id,
                        @AuthenticationPrincipal UserDetailsServiceImpl.CustomUser customUser,
                        Model model) {

        Map map = friendsDaoService.countForFriends(id);
        Friends friends = null;

        if (customUser.getUser().getId().equals(id)) {
            model.addAttribute("user", customUser.getUser());
        } else {
            try {
                friends = friendsDaoService.findUserAndFriendStatus(customUser.getUser().getId(), id);
                LOGGER.info("friends = " + friends);
                model.addAttribute("user", friends.getUser());
                model.addAttribute("status", friends.getStatus());
            } catch (NoResultException e) {
                LOGGER.warn("User by id " + id +", is not exist\n" + e);
                return "redirect:/user/id" + customUser.getUser().getId();
            }
        }

        if (customUser.getUser().getId().equals(id) || friends != null) {
            model.addAttribute("countFriends", map.get(FriendStatusEnum.PROGRESS.toString()));
            model.addAttribute("countFollowers",  map.get(FriendStatusEnum.FOLLOWER.toString()));
            model.addAttribute("countRequests",  map.get(FriendStatusEnum.REQUEST.toString()));
        }

        model.addAttribute("randomFriends", friendsDaoService.randomFriends(id, 6));
        model.addAttribute("tweets", feedServiceDao.findMyFeeds(id));

        return "user/my-home";
    }

    @ResponseBody
    @GetMapping(value = "/", produces={"application/json; charset=UTF-8"})
    public List allUser(@RequestParam(required = false) Integer page,
                        @RequestParam(required = false) Integer size) throws JsonProcessingException {
        LOGGER.info("page = " + page + ", size = " + size);
        if (size == null || page == null) {
            return userDaoService.findAll();
        }
        return userDaoService.findStandardUsers(new PageRequest(page, size));
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
            userDaoService.save(userRequest);
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
            return userDaoService.save(user);
        }
        return null;
    }

    // DELETE
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseBody
    @DeleteMapping(value = "/{id}")
    public void deleteUser(@PathVariable Long id) {
        userDaoService.delete(new User(id));
    }

    // GET FORM PROFILE
    @RequestMapping(value = "/{id}/put/view", method = RequestMethod.GET)
    public String profile(@PathVariable Long id,
                          @AuthenticationPrincipal UserDetailsServiceImpl.CustomUser customUser,
                          HttpServletRequest request,
                          Model model) {

        LOGGER.info("session user\n" + request.getSession().getAttribute("user"));

        if (id.equals(customUser.getUser().getId())) {
            model.addAttribute("user", customUser.getUser());
            return "user/form-my-profile";
        } else if (request.isUserInRole("ROLE_ADMIN")) {
            model.addAttribute("user", userDaoService.findOne(id));
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
        model.addAttribute("list", userDaoService.findStandardUsers(new PageRequest(0, size)));
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

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping (value = {"/role/{role}"}, method = RequestMethod.GET)
    public ModelAndView formAllUserByRole (@PathVariable String role) {
        int pageSize = 5;
        ModelAndView modelAndView = new ModelAndView("user/all-user");
        modelAndView.addObject("role", role);
        modelAndView.addObject("list", userDaoService.findUserByRoleId(UserRoleEnum.valueOf(role.toUpperCase()).getId()));
        modelAndView.addObject("pageSize", pageSize);
        return modelAndView;
    }

    @RequestMapping (value = {"/get/view"}, method = RequestMethod.GET)
    public ModelAndView formAllUserByRoleUser () {
        int pageSize = 5;
        ModelAndView modelAndView = new ModelAndView("user/all-user");
        modelAndView.addObject("role", "user");
        modelAndView.addObject("list", userDaoService.findAll());
        modelAndView.addObject("pageSize", pageSize);
        return modelAndView;
    }
}
