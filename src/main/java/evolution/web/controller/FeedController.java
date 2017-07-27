package evolution.web.controller;

import evolution.dao.FeedServiceDao;
import evolution.model.feed.Feed;
import evolution.model.user.StandardUser;
import evolution.model.user.User;
import evolution.service.MyJacksonService;
import evolution.service.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.io.IOException;
import java.util.Date;

/**
 * Created by Infant on 26.07.2017.
 */
@Controller
@RequestMapping(value = "/feed")
public class FeedController {

    @Autowired
    private FeedServiceDao feedServiceDao;

    @Autowired
    private MyJacksonService jacksonService;

    @GetMapping(value = "/user/{user_id}/get/view")
    public ModelAndView getNewsView(@PathVariable(value = "user_id") Long userId) {
        ModelAndView modelAndView = new ModelAndView("feed/feed-friends");
        modelAndView.addObject("list", feedServiceDao.findFeedsOfMyFriends(userId));
        return modelAndView;
    }

    @ResponseBody
    @GetMapping(value = "/user/{user_id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object getNewsByUserId(@PathVariable(value = "user_id") Long userId) {
        return feedServiceDao.findFeedsOfMyFriends(userId);
    }

    @PostMapping(value = "/post/view")
    public String postFeed(@RequestParam(name = "tweet-content") String tweetContent,
                           @AuthenticationPrincipal UserDetailsServiceImpl.CustomUser customUser,
                           @SessionAttribute User user) throws IOException {
        Feed feed = new Feed();
        feed.setContent(tweetContent);
        feed.setSender(customUser.getUser());
        feed.setDate(new Date());

        if (!user.getId().equals(customUser.getUser().getId())) {
           feed.setToUser(user);
        }

        if (!feed.getContent().isEmpty()) {
            feedServiceDao.save(feed);
        }
        return "redirect:/user/id" + user.getId();
    }

    @GetMapping(value = "/{id}/delete/view")
    public String deleteFeed(@PathVariable(value = "id") Long feedId,
                             @AuthenticationPrincipal UserDetailsServiceImpl.CustomUser customUser,
                             @SessionAttribute User user) {
        feedServiceDao.delete(feedId, customUser.getUser().getId());
        return "redirect:/user/id" + user.getId();
    }

    @GetMapping(value = "/feed-message/{id}/delete/view")
    public String deleteFeedMessage(@PathVariable(value = "id") Long feedId,
                                    @AuthenticationPrincipal UserDetailsServiceImpl.CustomUser customUser,
                                    @SessionAttribute User user) {
        feedServiceDao.deleteFeedMessage(feedId, customUser.getUser().getId());
        return "redirect:/user/id" + user.getId();
    }
}
