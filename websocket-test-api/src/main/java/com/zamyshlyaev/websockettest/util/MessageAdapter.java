package com.zamyshlyaev.websockettest.util;

import com.zamyshlyaev.websockettest.dto.MessageDto;
import com.zamyshlyaev.websockettest.entity.MessageEntity;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Adapter class for adapting MessageDto to MessageEntity and vice versa.
 */
public class MessageAdapter {

  /**
   * Adapt MessageDto object to MessageEntity and return.
   *
   * @param messageEntity instance of MessageEntity which must be adapted.
   * @return adapted instance of MessageDto.
   */
  public static MessageDto adapt(@NotNull MessageEntity messageEntity) {
    Objects.requireNonNull(messageEntity, "MessageEntity must be not null.");
    MessageDto messageDto = new MessageDto();
    messageDto.setValue(messageEntity.getValue());
    return messageDto;
  }

  /**
   * Adapt MessageEntity object to MessageDto and return.
   *
   * @param messageDto instance of MessageDto which must be adapted.
   * @return adapted instance of MessageEntity.
   */
  public static MessageEntity adapt(@NotNull MessageDto messageDto) {
    Objects.requireNonNull(messageDto, "MessageDto must be not null.");
    MessageEntity messageEntity = new MessageEntity();
    messageEntity.setValue(messageDto.getValue());
    return messageEntity;
  }
}
