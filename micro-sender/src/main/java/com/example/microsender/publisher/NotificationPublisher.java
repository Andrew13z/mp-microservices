package com.example.microsender.publisher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationPublisher {

  private final RabbitTemplate rabbitTemplate;

  @Value("${app.rabbitmq.exchange.name}")
  private String topicExchangeName;

  @Value("${spring.rabbitmq.host}")
  private String rabbitHost;

  @Value("${spring.rabbitmq.port}")
  private String rabbitPort;

  @Value("${app.rabbitmq.routing.key}")
  private String routingKey;

  public String sendNotification(String message) {
    log.info("Sending message '{}' to {}:{}", message, rabbitHost, rabbitPort);
    rabbitTemplate.convertAndSend(topicExchangeName, routingKey, message);
    log.info("Sent message '{}' to exchange '{}'.", message, topicExchangeName);
    return "Notification sent.";
  }
}
