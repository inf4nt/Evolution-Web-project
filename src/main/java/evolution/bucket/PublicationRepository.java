//package evolution.bucket;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import java.util.List;
//
///**
// * Created by Infant on 05.07.2017.
// */
//interface PublicationRepository extends JpaRepository<Publication, Long> {
//
//    @Query("select p from Publication p " +
//            " where p.sender.id = :id " +
//            " order by p.id desc")
//    List<Publication> findPublicationBySenderId(@Param("id") Long id);
//
//    List<Publication> findPublicationByCategory(Long id);
//
////    @Query(" delete from Publication p " +
////            " where p.id =:id and p.sender.id =:senderId ")
////    void delete(@Param("id") Long id, @Param("senderId") Long senderId);
//}
