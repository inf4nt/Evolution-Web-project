package evolution.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import evolution.dao.FriendsDao;
import evolution.dao.FeedDao;
import evolution.dao.UserDao;
import evolution.model.friend.Friends;
import evolution.model.user.User;
import evolution.service.MyJacksonService;
import evolution.service.SearchService;
import evolution.service.builder.JsonInformationBuilder;
import evolution.service.security.UserDetailsServiceImpl;
import evolution.service.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by Admin on 05.03.2017.
 */
@Controller
@RequestMapping ("/user")
@SessionAttributes(value = {"user"})
public class UserController {

    @Autowired
    private JsonInformationBuilder jsonInformationBuilder;
    @Autowired
    private UserDao userDao;
    @Autowired
    private MyJacksonService jacksonService;
    @Autowired
    private FriendsDao friendsDao;
    @Autowired
    private Validator validator;
    @Autowired
    private SearchService searchService;
    @Autowired
    private FeedDao newsDao;
    private Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @RequestMapping (value = "/id{id}", method = RequestMethod.GET)
    public String home (
            @PathVariable Long id,
            Model model,
            @SessionAttribute(required = false) User authUser,
            SessionStatus sessionStatus) {

        sessionStatus.setComplete();
        LOGGER.info("Session status set complete");

        if (authUser.getId().equals(id)) {
            model.addAttribute("user", authUser);
            LOGGER.info("My home. User id = " + id);
        } else {
            try {
                Friends friends = friendsDao.findUserAndFriendStatus(authUser.getId(), id);
                model.addAttribute("user", friends.getUser());
                model.addAttribute("status", friends.getStatus());
                LOGGER.info("Other user id = " + friends.getUser().getId());
            } catch (NoResultException e) {
                LOGGER.info("User by id " + id +", is not exist\n" + e.toString());
                return "redirect:/user/id" + authUser.getId();
            }
        }

        model.addAttribute("news", newsDao.allPosts(id, 100, 0));

        return "user/my-home";
    }

    // EDIT
    @ResponseBody @RequestMapping(value = "/{id}", method = RequestMethod.PUT,
            produces={"application/json; charset=UTF-8"})
    public String edit(@RequestBody String json,
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
            userDao.repository().update(userRequest);
            if (customUser.getUser().getId().equals(id))
                customUser.getUser().updateFields(userRequest);
            return jsonInformationBuilder.buildJson(HttpStatus.OK.toString(), null, true);
        }

        return jsonInformationBuilder.buildJson(HttpStatus.OK.toString(), null, false);
    }

    @RequestMapping(value = "/ex", method = RequestMethod.GET)
    public String ex() {
        throw new NullPointerException();
    }

    // DELETE
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseBody @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable Long id) {
        userDao.repository().delete(new User(id));
    }

    // GET FORM PROFILE
    @RequestMapping(value = "/profile/{id}", method = RequestMethod.GET)
    public String profile(@PathVariable Long id,
                          @AuthenticationPrincipal UserDetailsServiceImpl.CustomUser customUser,
                          HttpServletRequest request,
                          Model model) {

        if (id.equals(customUser.getUser().getId())) {
            model.addAttribute("user", customUser.getUser());
            return "user/form-my-profile";
        } else if (request.isUserInRole("ROLE_ADMIN")) {
            model.addAttribute("user", userDao.find(id));
            return "admin/admin-form-profile";
        }

        return "redirect:/user/id" + customUser.getUser().getId();
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String viewSearch(){
        return "user/new-search";
    }

    @ResponseBody @RequestMapping(value = "/search-result", method = RequestMethod.GET, produces={"application/json; charset=UTF-8"})
    public String resultSearch(@RequestParam String like) throws JsonProcessingException {
        return jacksonService.objectToJson(searchService.searchUser(like));
    }

}
