package evolution.repository;

import evolution.model.user.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByLogin(String login);

    @Query("select new User(id, firstName, lastName) from User where id = :id")
    User selectIdFirstLastName(@Param("id") Long id);

    @Query("select new User(u.id, u.firstName, u.lastName )from User u " +
            " where (lower(u.firstName) like lower (concat('%', :p1, '%')) and lower(u.lastName) like lower(concat('%', :p2, '%'))) " +
            " or (lower(u.lastName) like lower (concat('%', :p1, '%')) and lower(u.firstName) like lower(concat('%', :p2, '%')))")
    List<User> findUserByFirstLastName(@Param("p1") String p1, @Param("p2") String p2, Pageable pageable);

    @Query("select new User(u.id, u.firstName, u.lastName )from User u " +
            " where (lower(u.firstName) like lower (concat('%', :p1, '%'))) or (lower(u.lastName) like lower(concat('%', :p1, '%')))")
    List<User> findUserByFirstOrLastName(@Param("p1") String p1, Pageable pageable);

    @Query(" select new User(id, firstName, lastName) from User  ")
    List findUsers(Pageable pageable);

    @Query(" select new User(id, firstName, lastName) from User ")
    List findUsers();

//    @Query(" select new User(u,id, u.firstName, u.lastName) from User u where u.roleId =:role_id order by u.id desc ")
//    List<User> findAllByRole(@Param("role_id") Long roleId);
}

