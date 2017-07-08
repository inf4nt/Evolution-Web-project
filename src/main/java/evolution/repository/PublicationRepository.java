package evolution.repository;

import evolution.model.Publication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Infant on 05.07.2017.
 */
public interface PublicationRepository extends JpaRepository<Publication, Long> {

    List<Publication> findPublicationBySenderId(Long id);

    List<Publication> findPublicationByTheme(Long id);
}
