package evolution.service.builder;


import evolution.model.SecretQuestionType;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Admin on 11.03.2017.
 */
@Service
public class SecretQuestionTypeBuilderService {

    public SecretQuestionType build (Long id, HttpServletRequest request) {
        SecretQuestionType result = new SecretQuestionType();
        if (id != null)
            result.setId(id);
        result.setName(request.getParameter("sqt"));
        return result;
    }
}
