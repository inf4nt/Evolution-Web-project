package evolution.dao;

import evolution.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Infant on 05.07.2017.
 */
interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("select c from Comment c" +
            " join fetch c.sender" +
            " join fetch c.publication " +
            " where c.publication.id = :id " +
            " order by c.id desc ")
    List<Comment> findCommentByPublicationId(@Param("id") Long id);
}
