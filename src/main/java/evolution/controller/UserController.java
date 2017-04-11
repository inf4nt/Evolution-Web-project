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
import javax.servlet.http.HttpServletRequest;


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
        long authUserId = ((User) request.getSession().getAttribute("authUser")).getId();
        if (authUserId != id)
            model.addAttribute("user", userDao.findById(id));
        else
            model.addAttribute("user", request.getSession().getAttribute("authUser"));

        return "user/my-home";
    }

    @RequestMapping (value = {"/search/{action}"}, method = RequestMethod.GET)
    public String search (
            @PathVariable  String action,
            Model model, HttpServletRequest request, SessionStatus sessionStatus){

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
    }

    @RequestMapping (value = "/form-my-profile/{id}", method = RequestMethod.GET)
    public String profile (@PathVariable long id, Model model, HttpServletRequest request) {
        long authUserId = ((User) request.getSession().getAttribute("authUser")).getId();
        if (authUserId != id)
            model.addAttribute("user", userDao.findById(id));
        else
            model.addAttribute("user", request.getSession().getAttribute("authUser"));
        model.addAttribute("sqt", sqtDao.findAll());
        return "user/form-my-profile";
    }

    @RequestMapping (value = "/edit/{id}", method = RequestMethod.POST)
    public String edit (@PathVariable long id, HttpServletRequest request) {
        User user = userBuilderService.build(id, request);
        if (!validator.userValidator(user)) {
            return "redirect:/form-my-profile/" + id;
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

}
