package evolution.repository;

import evolution.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Infant on 05.07.2017.
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
