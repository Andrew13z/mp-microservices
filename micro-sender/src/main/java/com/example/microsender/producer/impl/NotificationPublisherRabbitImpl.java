package com.example.microsender.producer.impl;

import com.example.microsender.producer.NotificationPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationPublisherRabbitImpl implements NotificationPublisher {

  private final RabbitTemplate rabbitTemplate;

  @Value("${app.rabbitmq.topic.exchange.name}")
  private String topicExchangeName;

  @Value("${app.rabbitmq.routing.key}")
  private String routingKey;

  @Override
  public String sendNotification(String message) {
    rabbitTemplate.convertAndSend(topicExchangeName, routingKey, message);
    log.info("Sent message message '{}' to exchange '{}'.", message, topicExchangeName);
    return "Notification sent.";
  }
}
