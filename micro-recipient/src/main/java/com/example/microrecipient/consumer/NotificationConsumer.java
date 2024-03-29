package com.example.microrecipient.consumer;

import com.example.microrecipient.entity.Notification;
import com.example.microrecipient.repository.NotificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class NotificationConsumer {

  @Value("${app.rabbitmq.queue.name}")
  private String queueName;

  private final RabbitTemplate rabbitTemplate;
  private final NotificationRepository notificationRepository;

  @Autowired
  public NotificationConsumer(RabbitTemplate rabbitTemplate, NotificationRepository notificationRepository) {
    this.rabbitTemplate = rabbitTemplate;
    this.notificationRepository = notificationRepository;
  }

  @Scheduled(fixedDelay = 5_000L)
  public void processMessageFromQueue() {
    log.info("Scheduled retrieval of a message from the {} queue.", queueName);

    var messageOptional = convertMessage(rabbitTemplate.receive(queueName));

    if (messageOptional.isPresent()) {
      var message = messageOptional.get();
      log.info("Received message '{}' from the queue.", message);
      notificationRepository.save(new Notification(message));
      log.info("Message '{}' saved to the H2.", message);
    }
  }

  private Optional<String> convertMessage(Message message) {
    return Optional.ofNullable(message)
      .map(m -> new String(m.getBody()));
  }

}
