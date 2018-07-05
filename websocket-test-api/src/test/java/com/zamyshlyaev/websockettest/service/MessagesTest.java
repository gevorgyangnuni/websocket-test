package com.zamyshlyaev.websockettest.service;

import com.zamyshlyaev.websockettest.dto.MessageDto;
import com.zamyshlyaev.websockettest.repository.MessageEntityRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MessagesTest {

  @Autowired
  private Messages messages;

  @Autowired
  private MessageEntityRepository messageEntityRepository;

  @Test
  public void saveTest() {
    this.messageEntityRepository.deleteAll();
    MessageDto messageDto = new MessageDto("12345678");
    this.messages.save(messageDto);
  }

  @Test
  public void findAllTest() {
    this.messageEntityRepository.deleteAll();

    MessageDto messageDto = new MessageDto("12345678");
    this.messages.save(messageDto);

    List<MessageDto> messageDtos = this.messages.findAll();
    Assert.assertNotNull(messageDtos);
    Assert.assertEquals(1, messageDtos.size());
    Assert.assertEquals(new MessageDto("12345678"), messageDtos.get(0));
  }
}
