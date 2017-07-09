package evolution.repository;

import evolution.model.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Infant on 05.07.2017.
 */
public interface PublicationRepository extends JpaRepository<Publication, Long> {

//    @Query("select p from Publication p " +
//            " join fetch p.sender " +
//            " where p.sender.id = :id")
//    List<Publication> findPublicationBySenderId(@Param("id") Long id);

    List<Publication> findPublicationBySenderId(Long id);

    List<Publication> findPublicationByCategory(Long id);
}
