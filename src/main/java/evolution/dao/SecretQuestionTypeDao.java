package evolution.dao;

import evolution.model.SecretQuestionType;

import java.util.List;

/**
 * Created by Admin on 09.03.2017.
 */
public interface SecretQuestionTypeDao {

    void save(SecretQuestionType secretQuestionType);

    void update (SecretQuestionType secretQuestionType);

    List<SecretQuestionType> findAll ();

    SecretQuestionType findById (long id);

    SecretQuestionType findByName (String name);

    void delete (SecretQuestionType secretQuestionType);

    void deleteById (long id);

    String FIND_ALL = "from SecretQuestionType";

    String DELETE_BY_ID = "delete from SecretQuestionType where id =:i";

    String FIND_BY_NAME = "from SecretQuestionType where name =:n";
}
