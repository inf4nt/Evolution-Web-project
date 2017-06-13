package evolution.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import evolution.dao.FeedDao;
import evolution.dao.UserDao;
import evolution.model.news.Feed;
import evolution.service.MyJacksonService;
import evolution.service.security.UserDetailsServiceImpl;
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
    private UserDao userDao;
    @Autowired
    private FeedDao newsDao;
    @Autowired
    private MyJacksonService jacksonService;

    @ResponseBody @RequestMapping(value = "/user/{id}", method = RequestMethod.GET,
            produces={"application/json; charset=UTF-8"})
    public String getUserFeed(@PathVariable Long id) throws JsonProcessingException {
        List<Feed> news = newsDao.allPosts(id, 100, 0);

        return jacksonService.objectToJson(news);
    }


    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String feedPost(@AuthenticationPrincipal UserDetailsServiceImpl.CustomUser customUser,
                           @RequestParam String tweet) {

        if (tweet.length() > 0) {
            Feed news = new Feed(tweet, new Date(), customUser.getUser());
            newsDao.repository().save(news);
        }

        return "redirect:/user/id" + customUser.getUser().getId();
    }






}
