package evolution.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import evolution.dao.FeedRepository;
import evolution.model.news.Feed;
import evolution.model.user.User;
import evolution.service.MyJacksonService;
import evolution.service.security.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by Admin on 13.06.2017.
 */
@Controller
@RequestMapping(value = "/feed")
public class FeedController {

    @Autowired
    private FeedRepository feedRepository;
    @Autowired
    private MyJacksonService jacksonService;
    private final static Logger LOGGER = LoggerFactory.getLogger(FeedController.class);

    @ResponseBody
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET,
            produces={"application/json; charset=UTF-8"})
    public String getUserFeed(@PathVariable Long id) throws JsonProcessingException {
        List<Feed> news = feedRepository.feed(id, 100, 0);
        return jacksonService.objectToJson(news);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String feedPost(@SessionAttribute(required = false) User user,
                           @AuthenticationPrincipal UserDetailsServiceImpl.CustomUser customUser,
                           @RequestParam String tweet) {

        LOGGER.info("session user\n" + user);

        if (tweet.length() > 0) {
            Feed feed = new Feed(tweet, new Date(), customUser.getUser(), user);
            feedRepository.repository().save(feed);
        }

        return "redirect:/user/id" + user.getId();
    }


    @RequestMapping (value = "/{feedId}", method = RequestMethod.GET,
            produces={"application/json; charset=UTF-8"})
    public String feedDelete(@PathVariable Long feedId,
                             @SessionAttribute User user,
                             @AuthenticationPrincipal UserDetailsServiceImpl.CustomUser customUser) {

        feedRepository.delete(feedId, customUser.getUser().getId());
        return "redirect:/user/id" + user.getId();
    }

    @RequestMapping (value = "/repost/{feedId}", method = RequestMethod.GET,
            produces={"application/json; charset=UTF-8"})
    public String repostDelete(@PathVariable Long feedId,
                             @SessionAttribute User user,
                             @AuthenticationPrincipal UserDetailsServiceImpl.CustomUser customUser) {

        feedRepository.repostDelete(feedId, customUser.getUser().getId());
        return "redirect:/user/id" + user.getId();
    }

    // удаляю то что написали на моей стене
    @RequestMapping (value = "/delete-feed-on-my-board/{feedId}", method = RequestMethod.GET,
            produces={"application/json; charset=UTF-8"})
    public String userWrited(@PathVariable Long feedId,
                             @AuthenticationPrincipal UserDetailsServiceImpl.CustomUser customUser) {
        feedRepository.deleteFeedOnMyBoard(feedId, customUser.getUser().getId());
        return "redirect:/user/id" + customUser.getUser().getId();
    }


}
