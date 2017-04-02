package evolution.service.builder;

import org.springframework.beans.support.PagedListHolder;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.SessionAttributes;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Admin on 19.03.2017.
 */
@Service
public class PaginationService {

    public PagedListHolder pagedListHolder (List source) {
        PagedListHolder pagedListHolder = new PagedListHolder();
        pagedListHolder.setSource(source);
        pagedListHolder.setPageSize(10);
        return pagedListHolder;
    }

    public void getPage(String action, PagedListHolder pagedListHolder) {
        if (action.matches("[a-z]+")){
            if (action.equals("next"))
                pagedListHolder.nextPage();
            if (action.equals("prev"))
                pagedListHolder.previousPage();
        }
        if (action.matches("\\d+"))
            pagedListHolder.setPage(Integer.parseInt(action));
    }
}
