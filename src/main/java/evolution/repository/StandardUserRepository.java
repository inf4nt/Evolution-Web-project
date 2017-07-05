package evolution.repository;

import evolution.model.user.StandardUser;
import evolution.model.user.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Infant on 05.07.2017.
 */
public interface StandardUserRepository extends JpaRepository<StandardUser, Long> {

    @Query(" select u from StandardUser u where u.id = :id")
    StandardUser selectIdFirstLastName(@Param("id") Long id);

    @Query(" select u from StandardUser u " +
            " where (lower(u.firstName) like lower (concat('%', :p1, '%')) and lower(u.lastName) like lower(concat('%', :p2, '%'))) " +
            " or (lower(u.lastName) like lower (concat('%', :p1, '%')) and lower(u.firstName) like lower(concat('%', :p2, '%')))")
    List<StandardUser> findUserByFirstLastName(@Param("p1") String p1, @Param("p2") String p2, Pageable pageable);

    @Query(" select u from StandardUser u " +
            " where (lower(u.firstName) like lower (concat('%', :p1, '%'))) or (lower(u.lastName) like lower(concat('%', :p1, '%')))")
    List<StandardUser> findUserByFirstOrLastName(@Param("p1") String p1, Pageable pageable);

    @Query(" select u from StandardUser u ")
    List<StandardUser> findUsers(Pageable pageable);
}
