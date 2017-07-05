package evolution.repository;

import evolution.model.Publication;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Infant on 05.07.2017.
 */
public interface PublicationRepository extends JpaRepository<Publication, Long> {
}
