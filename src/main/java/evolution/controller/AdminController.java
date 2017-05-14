package evolution.controller;

import evolution.common.UserRoleEnum;
import evolution.dao.AdminDao;
import evolution.dao.SecretQuestionTypeDao;
import evolution.dao.UserDao;
import evolution.model.SecretQuestionType;
import evolution.model.User;
import evolution.model.form.SecretQuestionTypeForm;
import evolution.service.builder.PaginationService;
import evolution.service.builder.SecretQuestionTypeBuilderService;
import evolution.service.builder.UserBuilderService;
import evolution.service.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;


import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by Admin on 05.03.2017.
 */
@Controller
@RequestMapping("/admin")
@SessionAttributes({"servletName", "role", "productList", "userProfile"})
public class AdminController {

    @RequestMapping (value = "/remove-user/{id}", method = RequestMethod.GET)
    public String remove (@PathVariable long id, HttpServletRequest request) {
        userDao.deleteById(id);

        String servletName;
        try {
            servletName = request.getSession().getAttribute("servletName").toString();
        } catch (NullPointerException ne){
            return "redirect:/welcome";
        }

        if (servletName.equals("form-all")){
            int numberPage = ((PagedListHolder) request.getSession().getAttribute("productList")).getPage();
            ((PagedListHolder) request.getSession().getAttribute("productList")).getPageList().remove(new User(id));
            String role = request.getSession().getAttribute("role").toString();
            return "redirect:/admin/form-all/" + role + "/" + numberPage;
        }
        if (servletName.equals("")){

        }

        return "redirect:/welcome";
    }

    @RequestMapping (value = {"/form-all/{role}/{action}"}, method = RequestMethod.GET)
    public String formAllUser (
            @PathVariable String role,
            @PathVariable String action,
            Model model,
            HttpServletRequest request) {

        PagedListHolder pagedListHolder = null;

        if (action.equals("start")) {
            if (role.equals("user"))
                pagedListHolder = paginationService.pagedListHolder(adminDao.findAllUser());
            if (role.equals("admin"))
                pagedListHolder = paginationService.pagedListHolder(adminDao.findAllAdmin());
            model.addAttribute("productList", pagedListHolder);
        }

        else {
            pagedListHolder = (PagedListHolder) request.getSession().getAttribute("productList");
            paginationService.getPage(action, pagedListHolder);
        }
        model.addAttribute("servletName", "form-all");
        model.addAttribute("role", role);
        model.addAttribute("page_url", "/admin/form-all/" + role);
        return "admin/admin-form-search";
    }

    @RequestMapping(value = "/form-create-user", method = RequestMethod.GET)
    public String formCreateUser(){
        return "admin/form-create-user";
    }


    @RequestMapping(value = "/profile/{id}", method = RequestMethod.GET)
    public String userData(@PathVariable Long id,
                           @SessionAttribute User authUser,
                           Model model) {
        if (id.equals(authUser.getId())) {
            model.addAttribute("userProfile", authUser);
        } else {
            model.addAttribute("userProfile", userDao.findById(id));
        }
        return "admin/admin-form-profile";
    }

    @ResponseBody @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public void userDataEdit(@SessionAttribute User userProfile,
                               @SessionAttribute User authUser,
                               @RequestParam String role,
                               HttpServletRequest request) {
        User result;
        result = userBuilderService.requestBuild(false, UserRoleEnum.valueOf(role).getId(), userProfile, request);
        if (validator.userValidator(result)) {
            userDao.update(result);
            if (userProfile.getId().equals(authUser.getId())){
                authUser.updateFields(result);
            }
        }
    }




    @ResponseBody @RequestMapping(value = "/create-user", method = RequestMethod.POST)
    public String userSave (@RequestParam String role,
                            @RequestParam String login,
                            HttpServletRequest request) {

        try {
            userDao.findByLogin(login);
            return "info, User " + login + " is exist. Try again";
        } catch (NoResultException e){}


        User result = userBuilderService.requestBuild(true, UserRoleEnum.valueOf(role).getId(), null, request);
        if (validator.userValidator(result))
            userDao.save(result);
        return "Success";
    }


    @Autowired
    private UserDao userDao;
    @Autowired
    private UserBuilderService userBuilderService;
    @Autowired
    private Validator validator;
    @Autowired
    private AdminDao adminDao;
    @Autowired
    private SecretQuestionTypeDao sqtDao;
    @Autowired
    private SecretQuestionTypeBuilderService secretQuestionTypeBuilderService;
    @Autowired
    private PaginationService paginationService;


    @RequestMapping (value = "/form-create-sqt", method = RequestMethod.GET)
    public String formCreateSqt (Model model) {
        model.addAttribute("form", new SecretQuestionTypeForm());
        return "admin/form-create-sqt";
    }

    @RequestMapping (value = "/create-sqt", method = RequestMethod.POST)
    public String createSqt (@Valid @ModelAttribute("form") SecretQuestionTypeForm form,
                             BindingResult bindingResult, HttpServletRequest request, Model model) {
        if (bindingResult.hasErrors())
            return "admin/form-create-sqt";
        SecretQuestionType secretQuestionType = secretQuestionTypeBuilderService.build(null, request);
        try {
            sqtDao.save(secretQuestionType);
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("error", request.getParameter("sqt") + " is already exist");
            return "admin/form-create-sqt";
        }
        model.addAttribute("error", request.getParameter("sqt") + " create successful");
        return "admin/form-create-sqt";
    }

    @RequestMapping (value = "/form-all-sqt", method = RequestMethod.GET)
    public String allSqt (Model model) {
        List<SecretQuestionType> list = sqtDao.findAll();
        model.addAttribute("list", list);
        return "admin/form-all-sqt";
    }
}
