package com.akmal.socialmediaapi.repository;

import com.akmal.socialmediaapi.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query(value = """
            SELECT *
            FROM message
            WHERE sender_id = ?1
                AND receiver_id = ?2
               OR sender_id = ?2
                AND receiver_id = ?1
            """, nativeQuery = true)
    List<Message> findMessagesByFriendId(Long friendId, Long userId);
}
