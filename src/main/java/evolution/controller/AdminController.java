package evolution.controller;

import evolution.dao.AdminDao;
import evolution.dao.SecretQuestionTypeDao;
import evolution.dao.UserDao;
import evolution.model.user.User;
import evolution.service.builder.PaginationService;
import evolution.service.builder.SecretQuestionTypeBuilderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Admin on 05.03.2017.
 */
@Controller
@RequestMapping("/admin")
@SessionAttributes({"servletName", "role", "productList", "user"})
public class AdminController {

    @RequestMapping (value = "/remove-user/{id}", method = RequestMethod.GET)
    public String remove (@PathVariable Long id, HttpServletRequest request) {
        userDao.delete(new User(id));

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


    @Autowired
    private UserDao userDao;
    @Autowired
    private AdminDao adminDao;
    @Autowired
    private SecretQuestionTypeDao sqtDao;
    @Autowired
    private SecretQuestionTypeBuilderService secretQuestionTypeBuilderService;
    @Autowired
    private PaginationService paginationService;
}
