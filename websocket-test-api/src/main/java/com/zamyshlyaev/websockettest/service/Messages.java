package com.zamyshlyaev.websockettest.service;

import com.zamyshlyaev.websockettest.dto.MessageDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Service interface for message management.
 */
public interface Messages {

  /**
   * @return list of MessageDto objects.
   */
  List<MessageDto> findAll();

  /**
   * @param messageDto instance of MessageDto.
   */
  void save(MessageDto messageDto);
}
