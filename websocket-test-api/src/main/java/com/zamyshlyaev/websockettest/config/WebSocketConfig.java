package com.zamyshlyaev.websockettest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * Configuration class to config web socket endpoint,
 * path prefix and message broker endpoint.
 */
@Configuration
@EnableWebSocketMessageBroker
@EnableWebSocket
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

  /**
   * Set application destination prefix /api,
   * for example all endpoint must be start /api/save or /api/delete etc...
   * and enable message broking to path /messages
   *
   * @param registry instance of MessageBrokerRegistry
   */
  @Override
  public void configureMessageBroker(MessageBrokerRegistry registry) {
    registry.setApplicationDestinationPrefixes("/api")
        .enableSimpleBroker("/messages");
  }

  /**
   * Register stomp endpoint to /ws, for example ws://localhost:8080/ws,
   * and allow all Origins.
   *
   * @param registry instance of StompEndpointRegistry
   */
  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
    registry.addEndpoint("/ws")
        .setAllowedOrigins("*");
  }
}
