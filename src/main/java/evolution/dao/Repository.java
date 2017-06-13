package evolution.dao;



/**
 * Created by Admin on 13.06.2017.
 */
public interface Repository {

    void save(Object object);

    void update(Object object);

    void delete(Object object);

    Object merge(Object object);
}
