package evolution.controller;

import evolution.dao.SecretQuestionTypeDao;
import evolution.dao.UserDao;
import evolution.model.SecretQuestionType;
import evolution.model.User;
import evolution.model.form.SecretQuestionTypeForm;
import evolution.service.SecretQuestionTypeBuilderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by Admin on 05.03.2017.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping (value = "/remove-user/{id}", method = RequestMethod.GET)
    public String remove (@PathVariable long id, HttpServletRequest request) {
        userDao.deleteById(id);

        String servletName = request.getSession().getAttribute("servletName").toString();
        if (servletName.equals("form-all")){
            int numberPage = ((PagedListHolder) request.getSession().getAttribute("productList")).getPage();

            User remove = new User();
            remove.setId(id);

            ((PagedListHolder) request.getSession().getAttribute("productList")).getPageList().remove(remove);
            String role = request.getSession().getAttribute("role").toString();
            return "redirect:/user/form-all/" + role + "/" + numberPage;
        }
        if (servletName.equals("")){

        }

        return "redirect:/welcome";
    }

    @RequestMapping (value = "/form-all-role-admin", method = RequestMethod.GET)
    public String formAllRoleAdmin (Model model, HttpServletRequest request) {
        List<User> listAdmin = userDao.findAllAdmin();
        model.addAttribute("listAdmin", listAdmin);
        return "old/form-all-user";
    }

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

    @Autowired
    private UserDao userDao;
    @Autowired
    private SecretQuestionTypeDao sqtDao;
    @Autowired
    private SecretQuestionTypeBuilderService secretQuestionTypeBuilderService;
}
