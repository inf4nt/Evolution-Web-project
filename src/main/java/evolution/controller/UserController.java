package evolution.controller;




import evolution.dao.FriendsDao;
import evolution.dao.SecretQuestionTypeDao;


import evolution.dao.UserDao;
import evolution.model.User;
import evolution.service.builder.PaginationService;
import evolution.service.builder.UserBuilderService;
import evolution.service.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;


/**
 * Created by Admin on 05.03.2017.
 */
@Controller
@RequestMapping ("/user")
@SessionAttributes(value = {"role", "productList", "sqt", "user"})
public class UserController {

    @RequestMapping (value = "/id/{id}", method = RequestMethod.GET)
    public String home (
            @PathVariable long id, HttpServletRequest request,
            Model model, Authentication authentication, SessionStatus sessionStatus) {
        if (authentication == null)
            return "redirect:/welcome";

        sessionStatus.setComplete();
        model.addAttribute("user", userDao.findById(id));
        return "user/my-home";
    }

    @RequestMapping (value = {"/search/{action}"}, method = RequestMethod.GET)
    public String search (
            @PathVariable  String action,
            Model model, HttpServletRequest request, SessionStatus sessionStatus){

        try {

            String like = request.getParameter("like");
            PagedListHolder pagedListHolder;
            if (action.equals("start")) {
                if (like.length() > 32 || like.isEmpty()) {
                    sessionStatus.setComplete();
                    return "user/search";
                }
                pagedListHolder = paginationService
                        .pagedListHolder(userDao.searchByFistNameLastName(like, ((User) request.getSession().getAttribute("authUser")).getId()));
                model.addAttribute("productList", pagedListHolder);
            }

            else {
                pagedListHolder = (PagedListHolder) request.getSession().getAttribute("productList");
                paginationService.getPage(action, pagedListHolder);
            }
            model.addAttribute("page_url", "/user/search");
            return "user/search";

        } catch (Exception e){
            return "redirect:/welcome";
        }
    }

    @RequestMapping (value = "/form-my-profile/{id}", method = RequestMethod.GET)
    public String profile (@PathVariable long id, Model model, HttpServletRequest request) {
            User user;
            user = userDao.findById(id);
            if (user == null)
                return "redirect:/welcome";
            model.addAttribute("user", user);
            model.addAttribute("sqt", sqtDao.findAll());
            return "user/form-my-profile";
    }

    @RequestMapping (value = "/edit/{id}", method = RequestMethod.POST)
    public String edit (@PathVariable long id, HttpServletRequest request, Model model) {
        User user = userBuilderService.build(id, request);
        if (!validator.userValidator(user)) {
            return "redirect:/logout";
        }
        userDao.update(user);
        return "redirect:/user/form-my-profile/" + id;
    }

    @RequestMapping(value = "/friend-action/{action}/{userId}/{friendId}", method = RequestMethod.GET)
    public String servletFriend(
            @PathVariable Long userId,
            @PathVariable Long friendId,
            @PathVariable String action) {

        if (action.equals("accept-friend")){
            friendsDao.acceptFriend(userId, friendId);
            return "redirect:/user/" + userId + "/friend/start";
        }

        if (action.equals("delete-friend")){
            friendsDao.deleteFriend(userId, friendId);
            return "redirect:/user/" + userId + "/follower/start";
        }

        if (action.equals("delete-request")){
            friendsDao.deleteRequest(userId, friendId);
            return "redirect:/user/" + userId + "/request/start";
        }

        if (action.equals("request-friend")){
            friendsDao.friendRequest(userId, friendId);
            return "redirect:/user/" + userId + "/request/start";
        }

        return "redirect:/user/id/" + userId;
    }

    // FRIENDS
    @RequestMapping(value = "/{id}/{status}/{action}", method = RequestMethod.GET)
    public String friends (
            @PathVariable long id,
            @PathVariable String action,
            @PathVariable String status,
            Model model,
            HttpServletRequest request) {

        PagedListHolder pagedListHolder = null;

        if (action.equals("start")) {
            if (status.equals("friend"))
                pagedListHolder = paginationService
                        .pagedListHolder(friendsDao.findMyFriend(id));
            if (status.equals("follower"))
                pagedListHolder = paginationService
                        .pagedListHolder(friendsDao.findMyFollower(id));
            if (status.equals("request"))
                pagedListHolder = paginationService
                        .pagedListHolder(friendsDao.findMyRequest(id));
            model.addAttribute("productList", pagedListHolder);
        }

        else {
            pagedListHolder = (PagedListHolder) request.getSession().getAttribute("productList");
            paginationService.getPage(action, pagedListHolder);
        }
        model.addAttribute("user", userDao.findById(id));
        model.addAttribute("friendStatus", status);
        model.addAttribute("page_url", "/user/" + id + "/" + status);
        return "user/form-my-friend";
    }

    @Autowired
    private UserDao userDao;
    @Autowired
    private SecretQuestionTypeDao sqtDao;
    @Autowired
    private UserBuilderService userBuilderService;
    @Autowired
    private PaginationService paginationService;
    @Autowired
    private FriendsDao friendsDao;
    @Autowired
    private Validator validator;
}
