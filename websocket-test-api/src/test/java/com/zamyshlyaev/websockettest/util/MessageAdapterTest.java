package com.zamyshlyaev.websockettest.util;


import com.zamyshlyaev.websockettest.dto.MessageDto;
import com.zamyshlyaev.websockettest.entity.MessageEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class MessageAdapterTest {

  @Test
  public void adaptMessageDtoToMessageEntityTest() {
    MessageDto messageDto = new MessageDto("test");
    MessageEntity messageEntity = MessageAdapter.adapt(messageDto);

    Assert.assertNotNull(messageEntity);
    Assert.assertEquals("test", messageEntity.getValue());
  }

  @Test
  public void adaptMessageEntityToMessageDtoTest() {
    MessageEntity messageEntity = new MessageEntity("test");
    MessageDto messageDto = MessageAdapter.adapt(messageEntity);

    Assert.assertNotNull(messageDto);
    Assert.assertEquals("test", messageDto.getValue());
  }
}
