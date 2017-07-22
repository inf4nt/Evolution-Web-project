package evolution.web.controller;

import evolution.dao.TweetDaoService;
import evolution.model.tweet.Tweet;
import evolution.model.tweet.TweetTransient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Infant on 23.07.2017.
 */
@RestController
@RequestMapping(value = "/tweet")
public class TweetController {

    @Autowired
    private TweetDaoService tweetDaoService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Tweet> allTweets(@RequestParam(required = false, defaultValue = "0") int page,
                                 @RequestParam(required = false, defaultValue = "0") int size) {
        if (page == 0 || size == 0)
            return tweetDaoService.findAllTweet();
        return tweetDaoService.findAllTweet(new PageRequest(page, size));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Tweet findOneTweet(@PathVariable Long id) {
        return tweetDaoService.findOneTweet(id);
    }

    @GetMapping(value = "/user/{user_id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<TweetTransient> allTweetsByUserId (@PathVariable(value = "user_id") Long userId,
                                                   @RequestParam(required = false, defaultValue = "0") int limit,
                                                   @RequestParam(required = false, defaultValue = "0") int offset){
        if(limit == 0 || offset == 0)
            return tweetDaoService.findTweetsOfMyFriends(userId);
        return tweetDaoService.findTweetsOfMyFriends(userId, limit, offset);
    }
}
