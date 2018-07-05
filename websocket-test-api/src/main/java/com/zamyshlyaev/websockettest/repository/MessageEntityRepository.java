package com.zamyshlyaev.websockettest.repository;

import com.zamyshlyaev.websockettest.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * MessageEntity Repository standard spring data realisation.
 */
public interface MessageEntityRepository extends JpaRepository<MessageEntity, Long> {
}
