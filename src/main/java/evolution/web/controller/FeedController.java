package evolution.web.controller;

import evolution.dao.FeedServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Infant on 26.07.2017.
 */
@Controller
@RequestMapping(value = "/feed")
public class FeedController {

    @Autowired
    private FeedServiceDao feedServiceDao;

    @GetMapping(value = "/user/{user_id}/get/view")
    public ModelAndView getNewsView(@PathVariable(value = "user_id") Long userId) {
        ModelAndView modelAndView = new ModelAndView("feed/feed-friends");
        modelAndView.addObject("list", feedServiceDao.findFeedOfMyFriends(userId));
        return modelAndView;
    }

    @ResponseBody
    @GetMapping(value = "/user/{user_id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object getNewsByUserId(@PathVariable(value = "user_id") Long userId) {
        return feedServiceDao.findFeedOfMyFriends(userId);
    }

}
