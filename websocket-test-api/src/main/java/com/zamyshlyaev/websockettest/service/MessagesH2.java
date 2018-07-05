package com.zamyshlyaev.websockettest.service;

import com.zamyshlyaev.websockettest.dto.MessageDto;
import com.zamyshlyaev.websockettest.entity.MessageEntity;
import com.zamyshlyaev.websockettest.repository.MessageEntityRepository;
import com.zamyshlyaev.websockettest.util.MessageAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Messages management service realisation for H2 DB.
 */
@Service
public class MessagesH2 implements Messages {

  private final MessageEntityRepository repository;

  @Autowired
  public MessagesH2(MessageEntityRepository repository) {
    this.repository = repository;
  }

  /**
   * Load all messages from H2 db adapt/transform MessageEntity Objects to MessageDto and return to client.
   *
   * @return list of MessageDto objects.
   */
  @Override
  public List<MessageDto> findAll() {
    List<MessageEntity> messageEntities = this.repository.findAll();
    return messageEntities.stream().map(MessageAdapter::adapt).collect(Collectors.toList());
  }

  /**
   * Adapt MessageDto object to MessageEntity and save into H2 db.
   *
   * @param messageDto instance of MessageDto.
   */
  @Override
  public void save(MessageDto messageDto) {
    this.repository.save(MessageAdapter.adapt(messageDto));
  }
}
