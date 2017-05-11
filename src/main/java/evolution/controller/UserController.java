package evolution.controller;





import com.fasterxml.jackson.core.JsonProcessingException;
import evolution.dao.FriendsDao;
import evolution.dao.SecretQuestionTypeDao;
import evolution.dao.UserDao;
import evolution.model.User;
import evolution.model.UserFriend;
import evolution.service.MyJacksonService;
import evolution.service.SearchService;
import evolution.service.builder.PaginationService;
import evolution.service.builder.UserBuilderService;
import evolution.service.security.UserDetailsServiceImpl;
import evolution.service.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * Created by Admin on 05.03.2017.
 */
@Controller
@RequestMapping ("/user")
@SessionAttributes(value = {"role", "productList", "sqt", "user"})
public class UserController {

    @RequestMapping (value = "/id/{id}", method = RequestMethod.GET)
    public String home (
            @PathVariable long id,
            @SessionAttribute User authUser,
            Model model, Authentication authentication, SessionStatus sessionStatus) {
        if (authentication == null)
            return "redirect:/welcome";

        sessionStatus.setComplete();
        if (authUser.getId() == id) {
            model.addAttribute("user", authUser);
        } else {
            try {
                UserFriend user = userDao.findUserAndFriendStatus(authUser.getId(), id);
                model.addAttribute("user", user);
            } catch (NoResultException nre) {
                model.addAttribute("info", "This user does not exist");
            }
        }
        return "user/my-home";
//        return "user/dynamicHomePage";
    }

//    @RequestMapping (value = {"/search/{action}"}, method = RequestMethod.GET)
//    public String search (
//            @PathVariable  String action,
//            Model model, HttpServletRequest request){
//        PagedListHolder pagedListHolder;
//
//
//        if (action.equals("start")) {
//            try {
//                String like = request.getParameter("like");
//                pagedListHolder = paginationService.pagedListHolder(searchService.search(like));
//                model.addAttribute("productList", pagedListHolder);
//            } catch (NoResultException nre) {
//                return "redirect:/welcome";
//            }
//        } else {
//            pagedListHolder = (PagedListHolder) request.getSession().getAttribute("productList");
//            paginationService.getPage(action, pagedListHolder);
//        }
//
//        model.addAttribute("page_url", "/user/search");
//        return "user/search";
//    }

//    @ResponseBody @RequestMapping (value = "/profile", method = RequestMethod.GET)
//    public String profile (
//            @AuthenticationPrincipal UserDetailsServiceImpl.CustomUser customUser
//    ) throws JsonProcessingException {
//        User user = userDao.findById(customUser.getId());
//        return jacksonService.objectToJson(user);
//    }
//
//    @ResponseBody @RequestMapping (value = "/update", method = RequestMethod.POST)
//    public String edit (
//            @AuthenticationPrincipal UserDetailsServiceImpl.CustomUser customUser,
//            HttpServletRequest request) {
//
//        String res = request.getParameter("login") + " " + request.getParameter("password") + " " +
//                request.getParameter("sqtId") + " " + request.getParameter("secretQuestion") + " " +
//                request.getParameter("firstName") + " " + request.getParameter("lastName") + " " +
//                request.getParameter("role") + " " + request.getParameter("registrationDate");
//
//        return res;
//    }


    @RequestMapping (value = "/form-my-profile/{id}", method = RequestMethod.GET)
    public String profile (@PathVariable long id, Model model) {
            User user;
            user = userDao.findById(id);
            if (user == null)
                return "redirect:/welcome";
            model.addAttribute("user", user);
            model.addAttribute("sqt", sqtDao.findAll());
            return "user/form-my-profile";
    }

    // этот метод нужно засунуть в админа. А тут сделать простое редактирование . Ид брать из сессии
    @RequestMapping (value = "/edit/{id}", method = RequestMethod.POST)
    public String edit (@PathVariable long id, HttpServletRequest request, Model model) {
        User user = userBuilderService.build(id, request);
        if (!validator.userValidator(user)) {
            return "redirect:/logout";
        }
        userDao.update(user);
        return "redirect:/user/form-my-profile/" + id;
    }


    // FRIENDS
    @RequestMapping(value = "/{id}/{status}/{action}", method = RequestMethod.GET)
    public String friends (
            @PathVariable long id,
            @PathVariable String action,
            @PathVariable String status,
            Model model,
            HttpServletRequest request, SessionStatus sessionStatus) {

        PagedListHolder pagedListHolder = null;

        if (action.equals("start")) {
            sessionStatus.setComplete();
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
        if (pagedListHolder.getPageList().size() <= 0) {
            User user = userDao.selectFirstLastName(id);
            model.addAttribute("myFirstName", user.getFirstName());
            model.addAttribute("myLastName", user.getLastName());
        }
        model.addAttribute("id", id);
        model.addAttribute("friendStatus", status);
        model.addAttribute("page_url", "/user/" + id + "/" + status);
        return "user/form-my-friend";
    }

    @RequestMapping(value = "/friend-action/{action}/{friendId}", method = RequestMethod.GET)
    public String servletFriend(
            @PathVariable Long friendId,
            @AuthenticationPrincipal UserDetailsServiceImpl.CustomUser customUser,
            @PathVariable String action) {
        Long authUserId = customUser.getId();

        if (action.equals("accept-friend"))
            friendsDao.acceptFriend(authUserId, friendId);

        if (action.equals("delete-friend"))
            friendsDao.deleteFriend(authUserId, friendId);

        if (action.equals("delete-request"))
            friendsDao.deleteRequest(authUserId, friendId);

        if (action.equals("request-friend"))
            friendsDao.friendRequest(authUserId, friendId);

        return "redirect:/user/id/" + friendId;
    }


    @RequestMapping(value = "/search-test", method = RequestMethod.GET)
    public String viewSearch(){
        return "user/new-search";
    }


    @ResponseBody @RequestMapping(value = "/search-result", method = RequestMethod.GET, produces={"application/json; charset=UTF-8"})
    public String resultSearch(@RequestParam String like) throws JsonProcessingException {
        return jacksonService.objectToJson(searchService.searchUser(like));
    }



    @Autowired
    private UserDao userDao;
    @Autowired
    private SecretQuestionTypeDao sqtDao;
    @Autowired
    private MyJacksonService jacksonService;
    @Autowired
    private UserBuilderService userBuilderService;
    @Autowired
    private PaginationService paginationService;
    @Autowired
    private FriendsDao friendsDao;
    @Autowired
    private Validator validator;
    @Autowired
    private SearchService searchService;

}
