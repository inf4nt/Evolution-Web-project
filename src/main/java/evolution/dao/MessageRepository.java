package evolution.dao;


import evolution.model.message.Message;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;



interface MessageRepository extends JpaRepository<Message, Long> {

    @Query(" select new Message (m.id, m.message, m.dateDispatch, " +
            " sender.id, sender.firstName, sender.lastName, d.id) " +
            " from Message m " +
            " join m.dialog as d " +
            " join m.sender as sender " +
            " where (d.first.id =:id1 and d.second.id =:id2 ) " +
            " or (d.first.id =:id2 and d.second.id =:id1 ) order by m.id desc ")
    List<Message> findMessage(@Param("id1") Long id1, @Param("id2") Long id2, Pageable pageable);

    @Query(" select new Message (m.id, m.message, m.dateDispatch, " +
            " sender.id, sender.firstName, sender.lastName) " +
            " from Message m " +
            " join m.dialog as d " +
            " join m.sender as sender " +
            " where (d.first.id =:id1 and d.second.id =:id2 ) " +
            " or (d.first.id =:id2 and d.second.id =:id1 ) order by m.id desc ")
    List<Message> findMessageIgnoreDialog(@Param("id1") Long id1, @Param("id2") Long id2, Pageable pageable);

    @Query("select new Message (d.id, m.id, substring(m.message, 0, 40), m.dateDispatch," +
            " sender.id, sender.firstName, sender.lastName, " +
            " second.id, second.firstName, second.lastName) " +
            " from Message m " +
            " join m.sender as sender " +
            " join m.dialog as d" +
            " join StandardUser second on ( " +
            "       (second.id = d.first.id and d.first.id !=:id1 ) " +
            "       or (second.id = d.second.id and d.second.id !=:id1 ) " +
            " ) " +
            " where m.id in (" +
            " select max (m.id) " +
            " from Message m " +
            " join m.dialog as d " +
            " where d.first.id =:id1 " +
            " or d.second.id =:id1 " +
            " group by m.dialog.id ) " +
            " order by m.id desc ")
    List<Message> findLastMessageForDialog(@Param("id1") Long authUserId);
}
