package evolution.web.controller;

import evolution.common.PublicationCategory;
import evolution.model.Comment;
import evolution.model.Publication;
import evolution.model.user.StandardUser;
import evolution.repository.CommentRepository;
import evolution.repository.PublicationRepository;
import evolution.service.MyJacksonService;
import evolution.service.security.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Infant on 05.07.2017.
 */
@Controller
@RequestMapping(value = "/publication")
public class PublicationController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PublicationRepository publicationRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private MyJacksonService jacksonService;


    @RequestMapping(value = "/new")
    public ModelAndView formCreatePublication() {
        ModelAndView modelAndView = new ModelAndView("publication/create-publication");
        modelAndView.addObject("lengthContent", 30000);
        modelAndView.addObject("category", Arrays.asList(PublicationCategory.values()));
        return modelAndView;
    }

    @RequestMapping(value = "/action")
    public ModelAndView actionPublication() {
        ModelAndView modelAndView = new ModelAndView("publication/action-publication");
        return modelAndView;
    }















    @ResponseBody
    @RequestMapping(value = "/publication/{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Publication findById(@PathVariable Long id) {
        return publicationRepository.findOne(id);
    }

    @ResponseBody
    @RequestMapping(value = "/publication/", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Publication> findById() {
        return publicationRepository.findAll();
    }

    @ResponseBody
    @RequestMapping(value = "/publication/", method = RequestMethod.POST)
    public Publication savePublication(@RequestBody String json,
                                       @AuthenticationPrincipal UserDetailsServiceImpl.CustomUser customUser) throws IOException {
        Publication publication = (Publication) jacksonService.jsonToObject(json, Publication.class);
        publication.setDate(new Date());
        publication.setSender(new StandardUser(customUser.getUser().getId()));
        return publicationRepository.saveAndFlush(publication);
    }

    @RequestMapping(value = "/publication/{id}", method = RequestMethod.DELETE)
    public void deletePublication(@PathVariable Long id) throws IOException {
        publicationRepository.delete(id);
    }

    @ResponseBody
    @RequestMapping(value = "/publication/{id}/comment/", method = RequestMethod.POST)
    public Comment saveComment(@PathVariable Long id,
                               @RequestBody String json,
                               @AuthenticationPrincipal UserDetailsServiceImpl.CustomUser customUser) throws IOException {
//        Comment comment = (Comment) jacksonService.jsonToObject(json, Comment.class);
//        comment.setDate(new Date());
//        comment.setSender(new StandardUser(customUser.getUser().getId()));
//        comment.setPublication(new Publication(id));
//        return commentRepository.saveAndFlush(comment);
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/publication/{idPublication}/comment/", method = RequestMethod.GET)
    public List<Comment> getCommentByPublication(@PathVariable Long idPublication) throws IOException {
//        return publicationRepository.findOne(idPublication).getCommentList();
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/comment/{id}", method = RequestMethod.GET)
    public Comment commentById(@PathVariable Long id) throws IOException {
        return commentRepository.findOne(id);
    }

    @ResponseBody
    @RequestMapping(value = "/comment/", method = RequestMethod.GET)
    public List<Comment> commentById() throws IOException {
        return commentRepository.findAll();
    }

}
