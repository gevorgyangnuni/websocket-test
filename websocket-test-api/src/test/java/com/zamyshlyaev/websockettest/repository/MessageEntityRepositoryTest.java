package com.zamyshlyaev.websockettest.repository;

import com.zamyshlyaev.websockettest.entity.MessageEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MessageEntityRepositoryTest {

  @Autowired
  private MessageEntityRepository messageEntityRepository;

  @Test
  public void saveTest() {
    MessageEntity messageEntity = new MessageEntity("12345678");
    messageEntity = this.messageEntityRepository.save(messageEntity);

    Assert.assertNotNull(messageEntity);
    Assert.assertEquals(messageEntity.getValue(), "12345678");
    Assert.assertNotNull(messageEntity.getId());
    this.messageEntityRepository.deleteAll();
  }

  @Test
  public void findAllTest() {
    MessageEntity messageEntity = new MessageEntity("87654321");
    messageEntity = this.messageEntityRepository.save(messageEntity);
    List<MessageEntity> messageEntities = this.messageEntityRepository.findAll();

    Assert.assertNotNull(messageEntities);
    Assert.assertEquals(1, messageEntities.size());
    Assert.assertEquals(messageEntity, messageEntities.get(0));
    this.messageEntityRepository.deleteAll();
  }
}
