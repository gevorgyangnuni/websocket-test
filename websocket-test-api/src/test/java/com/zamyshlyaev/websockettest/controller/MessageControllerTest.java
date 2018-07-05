package com.zamyshlyaev.websockettest.controller;

import com.zamyshlyaev.websockettest.dto.MessageDto;
import com.zamyshlyaev.websockettest.service.Messages;
import org.apache.tomcat.websocket.WsWebSocketContainer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

import static java.util.concurrent.TimeUnit.SECONDS;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MessageControllerTest {

  @MockBean
  private Messages messages;

  @Value("${local.server.port}")
  private int port;

  private String WEB_SOCKET_URL;

  private CompletableFuture<List<MessageDto>> completableFuture;

  private static final String SEND_MESSAGE_ENDPOINT = "/api/send/message";
  private static final String SUBSCRIBE_SEND_MESSAGE_ENDPOINT = "/messages/show";

  @Before
  public void setup() {
    this.WEB_SOCKET_URL = "ws://localhost:" + this.port + "/" + "ws";
    this.completableFuture = new CompletableFuture<>();
  }

  @Test
  public void testSendMessageEndpoint() throws InterruptedException, ExecutionException, TimeoutException {
    WebSocketStompClient stompClient = new WebSocketStompClient(new StandardWebSocketClient(new WsWebSocketContainer()));
    stompClient.setMessageConverter(new MappingJackson2MessageConverter());

    StompSession stompSession = stompClient
        .connect(WEB_SOCKET_URL, new StompSessionHandlerAdapter() {
        })
        .get(1, SECONDS);

    String testMessage = "1234567";
    stompSession.subscribe(SUBSCRIBE_SEND_MESSAGE_ENDPOINT, new SendMessageStompFrameHandler());
    stompSession.send(SEND_MESSAGE_ENDPOINT, testMessage);

    Mockito.when(messages.findAll()).thenReturn(Collections.singletonList(new MessageDto("7654321")));

    List<MessageDto> messages = completableFuture.get(5, SECONDS);

    Assert.assertNotNull(messages);
    Assert.assertEquals(1, messages.size());
    Assert.assertEquals(new MessageDto("7654321"), messages.get(0));
  }

  private class SendMessageStompFrameHandler implements StompFrameHandler {

    @Override
    public Type getPayloadType(StompHeaders stompHeaders) {
      return List.class;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void handleFrame(StompHeaders stompHeaders, Object o) {
      List<MessageDto> messages = ((List<Map<String, String>>) o)
          .stream()
          .map(m -> new MessageDto(m.get("value")))
          .collect(Collectors.toList());
      MessageControllerTest.this.completableFuture.complete(messages);
    }
  }
}