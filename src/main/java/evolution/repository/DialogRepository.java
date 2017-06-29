package evolution.repository;

import evolution.model.dialog.Dialog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface DialogRepository extends JpaRepository<Dialog, Long> {
    @Query("select 1 from Dialog d " +
            " where (d.first.id =:id1 and d.second.id =:id2) " +
            " or (d.first.id =:id2 and d.second.id =:id1)")
    Integer checkDialog(@Param("id1") Long id1, @Param("id2") Long id2);
}
