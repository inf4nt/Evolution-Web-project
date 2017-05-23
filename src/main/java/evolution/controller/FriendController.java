package evolution.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import evolution.common.FriendStatusEnum;
import evolution.dao.FriendsDao;
import evolution.dao.UserDao;
import evolution.model.friend.Friends;
import evolution.model.user.User;
import evolution.service.MyJacksonService;
import evolution.service.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 21.05.2017.
 */
@Controller
@RequestMapping(value = "/friend")
public class FriendController {

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public String friends(@PathVariable Long userId,
                          @AuthenticationPrincipal UserDetailsServiceImpl.CustomUser customUser,
                          Model model,
                          HttpServletRequest request){


        int limit = 1;
        model.addAttribute("limit", limit);

        Map<String, List<Friends>> map;
        if (request.isUserInRole("ROLE_ADMIN") || customUser.getUser().getId().equals(userId)) {
            map = friendsDao.friend(userId, limit, 0);
            model.addAttribute("request", map.get("request"));
        } else {
            map = friendsDao.friendFollower(userId, limit, 0);
        }
        model.addAttribute("progress", map.get("progress"));
        model.addAttribute("follower", map.get("follower"));

        if (map.get("progress").size() > 0 )
            model.addAttribute("user", map.get("progress").get(0).getUser());
        else if (map.get("follower").size() > 0)
            model.addAttribute("user", map.get("follower").get(0).getUser());
        else if (map.get("request").size() > 0)
            model.addAttribute("user", map.get("request").get(0).getUser());
        else
            model.addAttribute("user", userDao.selectIdFirstLastName(userId));
        return "user/form-friend";

    }


    @ResponseBody @RequestMapping(value = "/{status}/{userId}", method = RequestMethod.GET, produces={"application/json; charset=UTF-8"})
    public String friend(@PathVariable Long userId,
                         @PathVariable String status,
                         @RequestParam Integer limit,
                         @RequestParam Integer offset,
                         HttpServletRequest request,
                         @AuthenticationPrincipal UserDetailsServiceImpl.CustomUser customUser) throws JsonProcessingException {

        List list = new ArrayList();

        if (status.toLowerCase().equals(FriendStatusEnum.PROGRESS.toString().toLowerCase())) {
            list = friendsDao.moreFriend(userId, limit, offset);
        }
        if (status.toLowerCase().equals(FriendStatusEnum.FOLLOWER.toString().toLowerCase())) {
            list =  friendsDao.moreFollower(userId, limit, offset);
        }
        if (request.isUserInRole("ROLE_ADMIN") || customUser.getUser().getId().equals(userId)){
            if (status.toLowerCase().equals(FriendStatusEnum.REQUEST.toString().toLowerCase())) {
                list =  friendsDao.moreRequest(userId, limit, offset);
            }
        }
        return jacksonService.objectToJson(list);
    }


    @RequestMapping(value = "/friend-action/{action}/{friendId}", method = RequestMethod.GET)
    public String servletFriend(
            @PathVariable Long friendId,
            @AuthenticationPrincipal UserDetailsServiceImpl.CustomUser customUser,
            @PathVariable String action) {
        Long authUserId = customUser.getUser().getId();

        if (action.equals("accept-friend"))
            friendsDao.acceptFriend(authUserId, friendId);

        if (action.equals("delete-friend"))
            friendsDao.deleteFriend(authUserId, friendId);

        if (action.equals("delete-request"))
            friendsDao.deleteRequest(authUserId, friendId);

        if (action.equals("request-friend")) {
            if (!friendsDao.checkFriends(authUserId, friendId)) {
                friendsDao.friendRequest(authUserId, friendId);
            }
        }

        return "redirect:/user/id" + friendId;
    }

    @Autowired
    private FriendsDao friendsDao;
    @Autowired
    private MyJacksonService jacksonService;
    @Autowired
    private UserDao userDao;
}
