package com.example.microcollector.scheduler;

import com.example.microcollector.client.MicroRecipientClient;
import com.example.microcollector.entity.Notification;
import com.example.microcollector.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class MessageScheduler {

  private final MicroRecipientClient client;
  private final NotificationRepository notificationRepository;

  @Scheduled(fixedDelay = 30_000L)
  public void getAndLogMessages() {
    var stringMessages = client.getAllMessages();

    var notificationEntities = stringMessages.stream()
      .map(m -> new Notification(m))
      .collect(Collectors.toList());
    notificationRepository.saveAll(notificationEntities);

    log.info("Received {} message(s) from micro-recipient service: {}.", stringMessages.size(), stringMessages);
  }
}
