package evolution.web.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import evolution.dao.FeedDaoService;
import evolution.model.feed.FeedData;
import evolution.model.feed.FeedPublication;
import evolution.model.user.StandardUser;
import evolution.repository.FeedDataRepository;
import evolution.repository.FeedPublicationRepository;
import evolution.service.MyJacksonService;
import evolution.service.security.UserDetailsServiceImpl;
import evolution.service.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value = "/feed")
public class FeedController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FeedPublicationRepository feedPublicationRepository;

    @Autowired
    private MyJacksonService jacksonService;

    @Autowired
    private FeedDaoService feedDaoService;

    @Autowired
    private Validator validator;

    @GetMapping(value = "/news")
    public String getPageNews(@AuthenticationPrincipal UserDetailsServiceImpl.CustomUser customUser,
                              Model model) {
        model.addAttribute("list", feedPublicationRepository.findAllNews(customUser.getUser().getId(), new PageRequest(0, 100)));
        return "feed/my-news";
    }

    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List getAllFeed() throws JsonProcessingException {
        return feedPublicationRepository.findAll();
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public FeedPublication getFeed(@PathVariable Long id) throws JsonProcessingException {
        return feedPublicationRepository.findOne(id);
    }

    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public FeedPublication saveFeed (@RequestBody String json,
                                     @AuthenticationPrincipal UserDetailsServiceImpl.CustomUser customUser) throws IOException {

        FeedPublication fp = (FeedPublication) jacksonService.jsonToObject(json, FeedPublication.class);
        FeedPublication feedPublication = new FeedPublication(new FeedData(fp.getFeedData().getContent(), null),
                new StandardUser(customUser.getUser().getId()),
                null);

        if (validator.feedPublicationValid(feedPublication)) {
            return feedDaoService.save(fp.getFeedData().getContent(), null, customUser.getUser().getId(), null);
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List getFeedByUser(@PathVariable Long id,
                              @RequestParam(required = false) Integer size,
                              @RequestParam(required = false) Integer page) throws JsonProcessingException {
        if (page == null || size == null)
            return feedPublicationRepository.findAll(id);
        return feedPublicationRepository.findAll(id, new PageRequest(page, size));
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@RequestParam String tweetContent,
                       @AuthenticationPrincipal UserDetailsServiceImpl.CustomUser customUser) {

        FeedPublication feedPublication = new FeedPublication(new FeedData(tweetContent, null),
                new StandardUser(customUser.getUser().getId()),
                null);

        if (validator.feedPublicationValid(feedPublication)) {
            feedDaoService.save(tweetContent, null, customUser.getUser().getId(), null);
        }

        return "redirect:/feed/news";
    }
}
