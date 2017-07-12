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
import org.springframework.web.servlet.ModelAndView;

import javax.print.attribute.standard.Media;
import java.io.IOException;
import java.util.Date;
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
    private FeedDataRepository feedDataRepository;

    @Autowired
    private FeedDaoService feedDaoService;

    @Autowired
    private Validator validator;

    @GetMapping(value = "/{id}/get/view")
    public ModelAndView getPageNews(@PathVariable Long id) {
        ModelAndView model = new ModelAndView("feed/my-news");
        model.addObject("list", feedPublicationRepository.findAllNews(id, new PageRequest(0, 100)));
        return model;
    }

    @PostMapping(value = "/post/view")
    public String saveFeed (@RequestParam String tweetContent,
                            @AuthenticationPrincipal UserDetailsServiceImpl.CustomUser customUser) throws IOException {


        FeedPublication feedPublication = new FeedPublication();
        feedPublication.setFeedData(new FeedData(tweetContent, null));
        feedPublication.setDate(new Date());
        feedPublication.setSender(new StandardUser(customUser.getUser().getId()));
        feedPublication.setReposted(null);

        if (validator.feedPublicationValid(feedPublication)) {
            feedDaoService.save(feedPublication);
        }
        return "redirect:/feed/" + customUser.getUser().getId() + "/get/view";
    }

    @GetMapping(value = "/{id}/delete/view")
    public String saveFeed (@PathVariable Long id,
                            @AuthenticationPrincipal UserDetailsServiceImpl.CustomUser customUser) throws IOException {
        feedDataRepository.deletePost(id, customUser.getUser().getId());
        return "redirect:/feed/" + customUser.getUser().getId() + "/get/view";
    }


    @ResponseBody
    @GetMapping(value = "/tag/{tag}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<FeedPublication> findByTag(@PathVariable(required = false) String tag) {
        if (tag != null) {
            return feedPublicationRepository.findByTags(tag.toLowerCase());
        }
        return null;
    }








//    @ResponseBody
//    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public FeedPublication saveFeed (@RequestBody String json,
//                            @AuthenticationPrincipal UserDetailsServiceImpl.CustomUser customUser) throws IOException {
//
//        FeedPublication fp = (FeedPublication) jacksonService.jsonToObject(json, FeedPublication.class);
//        FeedPublication feedPublication = new FeedPublication(new FeedData(fp.getFeedData().getContent(), null),
//                new StandardUser(customUser.getUser().getId()),
//                null);
//
//        if (validator.feedPublicationValid(feedPublication)) {
//            return feedDaoService.save(fp.getFeedData().getContent(), null, customUser.getUser().getId(), null);
//        }
//        return null;
//    }









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

//    @ResponseBody
//    @RequestMapping(value = "/", method = RequestMethod.POST,
//            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public FeedPublication saveFeed (@RequestBody String json,
//                                     @AuthenticationPrincipal UserDetailsServiceImpl.CustomUser customUser) throws IOException {
//
//        FeedPublication fp = (FeedPublication) jacksonService.jsonToObject(json, FeedPublication.class);
//        FeedPublication feedPublication = new FeedPublication(new FeedData(fp.getFeedData().getContent(), null),
//                new StandardUser(customUser.getUser().getId()),
//                null);
//
//        if (validator.feedPublicationValid(feedPublication)) {
//            return feedDaoService.save(fp.getFeedData().getContent(), null, customUser.getUser().getId(), null);
//        }
//        return null;
//    }

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
