//package evolution.web.controller;
//
//import evolution.common.PublicationCategoryEnum;
//import evolution.model.Comment;
//import evolution.model.Publication;
//import evolution.model.user.StandardUser;
//import evolution.repository.CommentRepository;
//import evolution.repository.PublicationRepository;
//import evolution.repository.StandardUserRepository;
//import evolution.service.MyJacksonService;
//import evolution.service.security.UserDetailsServiceImpl;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//
///**
// * Created by Infant on 05.07.2017.
// */
//@Controller
//@RequestMapping(value = "/publication")
//public class PublicationController {
//
//    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
//
//    @Autowired
//    private PublicationRepository publicationRepository;
//
//    @Autowired
//    private CommentRepository commentRepository;
//
//    @Autowired
//    private StandardUserRepository standardUserRepository;
//
//    @Autowired
//    private MyJacksonService jacksonService;
//
//
//    @RequestMapping(value = "/post/view", method = RequestMethod.GET)
//    public ModelAndView formCreatePublication() {
//        ModelAndView modelAndView = new ModelAndView("publication/create-publication");
//        modelAndView.addObject("lengthContent", 30000);
//        modelAndView.addObject("lengthSubject", 255);
//        modelAndView.addObject("category", Arrays.asList(PublicationCategoryEnum.values()));
//        return modelAndView;
//    }
//
//    @RequestMapping(value = "/user/{id}/get/view", method = RequestMethod.GET)
//    public ModelAndView listPublicationByUser(@PathVariable Long id) {
//        ModelAndView modelAndView = new ModelAndView("publication/list-publication");
//        List<Publication> list = publicationRepository.findPublicationBySenderId(id);
//        if (list.isEmpty()) {
//            modelAndView.addObject("user", standardUserRepository.findOne(id));
//        } else {
//            modelAndView.addObject("user", list.get(0).getSender());
//        }
//
//        modelAndView.addObject("list", list);
//        modelAndView.addObject("pageSize", 5);
//        return modelAndView;
//    }
//
//    @RequestMapping(value = "/{id}/get/view", method = RequestMethod.GET)
//    public ModelAndView listPublicationById(@PathVariable Long id) {
//        ModelAndView modelAndView = new ModelAndView("publication/publication");
//        modelAndView.addObject("publication", publicationRepository.findOne(id));
//        return modelAndView;
//    }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//    @ResponseBody
//    @RequestMapping(value = "/user/{id}",
//            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public Object findPublicationBySenderId(@PathVariable Long id) {
////        return publicationRepository.findPublicationBySenderId(id);
//        return null;
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "/category/{category}",
//            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public Object findPublicationByCategory(@PathVariable String category) {
//        try {
//            return publicationRepository.findPublicationByCategory(PublicationCategoryEnum.valueOf(category.toUpperCase()).getId());
//        } catch (IllegalArgumentException e) {
//            return null;
//        }
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "/", method = RequestMethod.POST)
//    public Publication savePublication(@RequestBody String json,
//                                       @AuthenticationPrincipal UserDetailsServiceImpl.CustomUser customUser,
//                                       @RequestParam String category) throws IOException {
//
//
//        Publication publication = (Publication) jacksonService.jsonToObject(json, Publication.class);
//        publication.setDate(new Date());
//        publication.setSender(new StandardUser(customUser.getUser().getId()));
//        try {
//            publication.setCategory(PublicationCategoryEnum.valueOf(category.toUpperCase()).getId());
//        } catch (IllegalArgumentException e) {
//            e.printStackTrace();
//            LOGGER.warn(e.toString());
//            return null;
//        }
//
//        LOGGER.info(publication.toString());
//
//        return publicationRepository.saveAndFlush(publication);
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    public void deletePublication(@PathVariable Long id,
//                                  @AuthenticationPrincipal UserDetailsServiceImpl.CustomUser customUser) throws IOException {
//        LOGGER.info(id.toString());
//        LOGGER.info(customUser.toString());
//        publicationRepository.delete(id);
//    }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//    @ResponseBody
//    @RequestMapping(value = "/publication/{id}", method = RequestMethod.GET,
//            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public Publication findById(@PathVariable Long id) {
//        return publicationRepository.findOne(id);
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "/publication/", method = RequestMethod.GET,
//            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public List<Publication> findById() {
//        return publicationRepository.findAll();
//    }
//
//
//
//
//    @ResponseBody
//    @RequestMapping(value = "/publication/{id}/comment/", method = RequestMethod.POST)
//    public Comment saveComment(@PathVariable Long id,
//                               @RequestBody String json,
//                               @AuthenticationPrincipal UserDetailsServiceImpl.CustomUser customUser) throws IOException {
////        Comment comment = (Comment) jacksonService.jsonToObject(json, Comment.class);
////        comment.setDate(new Date());
////        comment.setSender(new StandardUser(customUser.getUser().getId()));
////        comment.setPublication(new Publication(id));
////        return commentRepository.saveAndFlush(comment);
//        return null;
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "/publication/{idPublication}/comment/", method = RequestMethod.GET)
//    public List<Comment> getCommentByPublication(@PathVariable Long idPublication) throws IOException {
////        return publicationRepository.findOne(idPublication).getCommentList();
//        return null;
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "/comment/{id}", method = RequestMethod.GET)
//    public Comment commentById(@PathVariable Long id) throws IOException {
//        return commentRepository.findOne(id);
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "/comment/", method = RequestMethod.GET)
//    public List<Comment> commentById() throws IOException {
//        return commentRepository.findAll();
//    }
//
//}
