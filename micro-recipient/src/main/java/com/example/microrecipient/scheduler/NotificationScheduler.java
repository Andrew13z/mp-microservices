package com.example.microrecipient.scheduler;

import com.example.microrecipient.consumer.NotificationConsumer;
import com.example.microrecipient.mapper.NotificationMapper;
import com.example.microrecipient.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationScheduler {

  private final NotificationConsumer notificationConsumer;
  private final NotificationRepository notificationRepository;
  private final NotificationMapper notificationMapper;

  @Scheduled(fixedDelay = 10_000L)
  public void scheduledMessageReadTask() {
    log.info("Scheduled save of messages to the DB.");
    notificationConsumer.getAndClearMessages()
            .stream()
            .map(notificationMapper::fromString)
            .forEach(notificationRepository::save);
  }
}
