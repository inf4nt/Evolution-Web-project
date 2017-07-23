package evolution.web.controller;

import evolution.dao.TweetDaoService;
import evolution.dao.UserDaoService;
import evolution.model.tweet.Tweet;
import evolution.model.tweet.TweetTransient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Infant on 23.07.2017.
 */
@Controller
@RequestMapping(value = "/tweet")
public class TweetController {

    @Autowired
    private TweetDaoService tweetDaoService;

    @Autowired
    private UserDaoService userDaoService;

    @GetMapping(value = "/{user_id}/get/view")
    public ModelAndView getFriendsTweetForUser(@PathVariable(value = "user_id") Long userId) {
        ModelAndView modelAndView = new ModelAndView("tweet/friends-tweet");
        modelAndView.addObject("tweets", tweetDaoService.findTweetsOfMyFriends(userId));
        modelAndView.addObject("user", userDaoService.selectIdFirstLastNameStandardUser(userId));
        return modelAndView;
    }

    @ResponseBody
    @GetMapping(value = "/user/{user_id}/get/view", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object findTweetsByUserId(@PathVariable(value = "user_id") Long userId) {
        return tweetDaoService.findMyTweets(userId);
    }

    @ResponseBody
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Tweet> allTweets(@RequestParam(required = false, defaultValue = "0") int page,
                                 @RequestParam(required = false, defaultValue = "0") int size) {
        if (page == 0 || size == 0)
            return tweetDaoService.findAllTweet();
        return tweetDaoService.findAllTweet(new PageRequest(page, size));
    }

    @ResponseBody
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Tweet findOneTweet(@PathVariable Long id) {
        return tweetDaoService.findOneTweet(id);
    }

    @ResponseBody
    @GetMapping(value = "/user/{user_id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<TweetTransient> allTweetsByUserId (@PathVariable(value = "user_id") Long userId,
                                                   @RequestParam(required = false, defaultValue = "0") int limit,
                                                   @RequestParam(required = false, defaultValue = "0") int offset){
        if(limit == 0 || offset == 0)
            return tweetDaoService.findTweetsOfMyFriends(userId);
        return tweetDaoService.findTweetsOfMyFriends(userId, limit, offset);
    }
}
