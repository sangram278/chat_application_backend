package com.app.chatapp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.chatapp.entity.Message;
import com.app.chatapp.entity.Users;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

	List<Message> findBySenderAndReceiver(Users sender, Users receiver);
    List<Message> findByReceiverAndSeenFalse(Users receiver);
    @Query("""
    		   SELECT m FROM Message m 
    		   WHERE (m.sender.userId = :userId AND m.receiver.userId = :contactId)
    		      OR (m.sender.userId = :contactId AND m.receiver.userId = :userId)
    		   ORDER BY m.timestamp ASC
    		""")
    		List<Message> findChatBetweenUsers(
    		    @Param("userId") Long userId,
    		    @Param("contactId") Long contactId
    		);

}
