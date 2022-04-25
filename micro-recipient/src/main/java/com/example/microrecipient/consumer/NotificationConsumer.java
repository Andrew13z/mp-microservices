package com.example.microrecipient.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class NotificationConsumer {

  private final List<String> messages = new ArrayList<>();

  @RabbitListener(queues = "notification_queue")
  public void processMessageFromQueue(String message) {
    log.info("Received message '{}'.", message);
    messages.add(message);
  }

  public List<String> getAndClearMessages() {
    List<String> copy = new ArrayList<>();
    if (!messages.isEmpty()) {
      copy.addAll(messages);
      messages.clear();
    }
    return copy;
  }
}
