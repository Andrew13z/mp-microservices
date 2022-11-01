package com.example.microcollector.scheduler;

import com.example.microcollector.client.MicroRecipientClient;
import com.example.microcollector.entity.Notification;
import com.example.microcollector.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class MessageScheduler {

  private final MicroRecipientClient client;
  private final NotificationRepository notificationRepository;

  @Scheduled(fixedDelay = 25_000L)
  public void getAndLogMessages() {
    log.info("Scheduled retrieval of a message from the micro-recipient service.");
    var message = client.getAndDeleteSingleMessage();
    if (message != null) {
      log.info("Received message from micro-recipient service: {}.", message);
      notificationRepository.save(new Notification(message));
      log.info("Message '{}' saved to Postgres", message);
    }
  }
}
