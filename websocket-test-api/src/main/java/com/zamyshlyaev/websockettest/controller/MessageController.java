package com.zamyshlyaev.websockettest.controller;

import com.zamyshlyaev.websockettest.dto.MessageDto;
import com.zamyshlyaev.websockettest.service.Messages;
import com.zamyshlyaev.websockettest.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Controller class to handle received messages.
 */
@Controller
public class MessageController {

  private final Logger logger = LoggerFactory.getLogger(MessageController.class);
  private final Messages messages;

  @Autowired
  public MessageController(Messages messages) {
    this.messages = messages;
  }

  /**
   * Handle message save request from client,
   * save into H2 db, load all existing messages from db and send to client.
   *
   * @param message instance of MessageDto
   * @return List of MessageDtos
   */
  @MessageMapping("/send/message")
  @SendTo("/messages/show")
  public List<MessageDto> saveMessage(MessageDto message) {
    if (message != null && !StringUtils.isEmpty(message.getValue())) {
      String rawMessage = message.getValue().trim();
      message.setValue(StringUtils.reverse(rawMessage));
      this.logger.info("User message reversed from {} to {}", rawMessage, message.getValue());
      this.messages.save(message);
      this.logger.info("{} Message successfully saved", message.getValue());
    }
    return this.messages.findAll();
  }
}
