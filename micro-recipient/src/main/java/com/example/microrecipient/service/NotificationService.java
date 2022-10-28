package com.example.microrecipient.service;

import com.example.microrecipient.entity.Notification;
import com.example.microrecipient.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

  private final NotificationRepository notificationRepository;

  public List<String> getAndDeleteAllMessages() {
    var messages = notificationRepository.findAll().stream()
        .map(Notification::getMessage)
        .collect(toList());

    log.info("Received {} message(s) from the DB.", messages.size());

    notificationRepository.deleteAllInBatch();
    log.info("Deleted all messages from the DB.");

    return messages;
  }
}
