package evolution.controller;



import evolution.common.RestStatus;
import evolution.dao.FriendsDao;
import evolution.dao.SecretQuestionTypeDao;


import evolution.dao.UserDao;
import evolution.model.User;
import evolution.model.form.UserForm;
import evolution.service.PaginationService;
import evolution.service.UserBuilderService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by Admin on 05.03.2017.
 */
@Controller
@RequestMapping ("/user")
@SessionAttributes(value = {"servletName", "role", "productList", "sqt", "user"})
public class UserController {

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home (Authentication authentication, SessionStatus sessionStatus, Model model) {
        if (authentication == null)
            return "redirect:/";
        sessionStatus.setComplete();
        return "user/home";
    }


    @RequestMapping (value = {"/form-all/{role}/{action}"}, method = RequestMethod.GET)
    public String formAllUser (
            @PathVariable String role,
            @PathVariable String action,
            Model model,
            HttpServletRequest request) {

        PagedListHolder pagedListHolder = null;

        if (action.equals("start")) {
            if (role.equals("user"))
                pagedListHolder = paginationService.pagedListHolder(userDao.findAllUser());
            if (role.equals("admin"))
                pagedListHolder = paginationService.pagedListHolder(userDao.findAllAdmin());
            model.addAttribute("productList", pagedListHolder);
        }

        else {
            pagedListHolder = (PagedListHolder) request.getSession().getAttribute("productList");
            paginationService.getPage(action, pagedListHolder);
        }
        model.addAttribute("servletName", "form-all");
        model.addAttribute("role", role);
        model.addAttribute("page_url", "/user/form-all/" + role);
        return "user/form-search";
    }

    @RequestMapping (value = "/edit/{id}", method = RequestMethod.GET)
    public String edit (@PathVariable long id,
                        @Valid @ModelAttribute("form") UserForm form,
                        BindingResult bindingResult,
                        HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            return "user/form-my-profile";
        }
        User user = userBuilderService.build(id, request);
        userDao.update(user);
        return "redirect:/user/form-my-profile/" + id;
    }

    @RequestMapping (value = {"/search/{action}"}, method = RequestMethod.GET)
    public String search (
            @PathVariable  String action,
            Model model, HttpServletRequest request, SessionStatus sessionStatus){

        String like = request.getParameter("like");

        String role = request.getSession().getAttribute("authorities").toString();
        PagedListHolder pagedListHolder;

        if (action.equals("start")) {
            if (like.length() > 32 || like.isEmpty()) {
                sessionStatus.setComplete();
                return "user/form-search";
            }

            if (role.equals("[ROLE_ADMIN]"))
                pagedListHolder = paginationService.pagedListHolder(userDao.findLikeLogin("%" + like + "%"));
            else
                pagedListHolder = paginationService.pagedListHolder(userDao.findUserLikeLogin("%" + like + "%"));
            model.addAttribute("productList", pagedListHolder);
        }

        else {
            pagedListHolder = (PagedListHolder) request.getSession().getAttribute("productList");
            paginationService.getPage(action, pagedListHolder);
        }
        model.addAttribute("page_url", "/user/search");
        return "user/form-search";
    }


    @RequestMapping (value = "/form-my-profile/{id}", method = RequestMethod.GET)
    public String myProfile (@PathVariable long id, Model model) {
        User user = userDao.findById(id);
        model.addAttribute("user", user);
        model.addAttribute("sqt", sqtDao.findAll());
        model.addAttribute("form", new UserForm());
        return "user/form-my-profile";
    }

    @RequestMapping (value = "/reset-password", method = RequestMethod.GET)
    public String reset (Model model, HttpServletRequest request) {
//        try {
//            if (request.getSession().getAttribute("step").toString().equals("one")){
//                if (!frpv.isValidStepOne(request)) {
//                    model.addAttribute("error", frpv.getErrorList());
//                    model.addAttribute("step", "one");
//                    return "user/form-reset";
//                } else if (frpv.isValidStepTwo(request)) {
//                    User user = userDao.findByUserName(request.getParameter("username"));
//                    request.getSession().setAttribute("username", user.getName());
//                    model.addAttribute("step", "two");
//                    request.getSession().setAttribute("userid", user.getId());
//                    request.getSession().setAttribute("step","two");
//                    return "user/form-reset";
//                }
//            }
//
//            if (!frpv.isValidStepTwo(request)) {
//                model.addAttribute("error", frpv.getErrorList());
//                model.addAttribute("step", "two");
//                return "user/form-reset";
//            }
//            long id;
////            try {
////                id = Long.parseLong(request.getSession().getAttribute("userid").toString());
////            } catch (NullPointerException e) {
////                request.getSession().setAttribute("step", "one");
////                return "user/form-reset";
////            }
//            id = Long.parseLong(request.getSession().getAttribute("userid").toString());
//            userDao.reset(request.getParameter("newPassword").toString(), id);
//            model.addAttribute("step", "three");
//            request.getSession().setAttribute("userid", null);
//            request.getSession().setAttribute("username", null);
//            return "user/form-reset";
//        } catch (NullPointerException e) {
//            request.getSession().setAttribute("step", "one");
//            return "user/form-reset";
//        }
        return null;
    }

    @RequestMapping (value = "/form-reset-password", method = RequestMethod.GET)
    public String formResetPassword (Model model, HttpServletRequest request) {
        request.getSession().setAttribute("step", "one");
        return "user/form-reset";
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






    @RequestMapping(value = "/friend/{friendStatus}/{action}", method = RequestMethod.GET)
    public String friends (
            @PathVariable String action,
            @PathVariable String friendStatus,
            Model model,
            HttpServletRequest request) {

        PagedListHolder pagedListHolder = null;

        if (action.equals("start")) {
            if (friendStatus.equals("Friend"))
                pagedListHolder = paginationService.pagedListHolder(friendsDao.findMyFriend((Long) request.getSession().getAttribute("userid")));
            if (friendStatus.equals("Follower"))
                pagedListHolder = paginationService.pagedListHolder(friendsDao.findMyFollower((Long) request.getSession().getAttribute("userid")));

            model.addAttribute("productList", pagedListHolder);
        }

        else {
            pagedListHolder = (PagedListHolder) request.getSession().getAttribute("productList");
            paginationService.getPage(action, pagedListHolder);
        }
        model.addAttribute("friendStatus", friendStatus);
        model.addAttribute("page_url", "/user/friend/" + friendStatus);
        return "user/form-friends";
    }

    @RequestMapping(value = "/friend-action/{action}/{userId}/{friendId}", method = RequestMethod.GET)
    public String servletFriend(
            @PathVariable Long userId,
            @PathVariable Long friendId,
            @PathVariable String action) {

        if (action.equals("accept-friend")){
            friendsDao.acceptFriend(userId, friendId);
        }

        return "redirect:/user/friend/Friend/start";
    }


}
