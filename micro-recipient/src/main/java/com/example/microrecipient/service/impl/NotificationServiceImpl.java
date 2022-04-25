package com.example.microrecipient.service.impl;

import com.example.microrecipient.entity.Notification;
import com.example.microrecipient.repository.NotificationRepository;
import com.example.microrecipient.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceImpl implements NotificationService {

  private final NotificationRepository notificationRepository;

  @Override
  public List<String> getAndDeleteAllMessages() {
    var messages = new ArrayList<String>();
    notificationRepository.findAll().forEach(notification -> messages.add(notification.getMessage()));
    log.info("Received {} message(s) from the DB.", messages.size());

    notificationRepository.deleteAll();
    log.info("Deleted all messages from the DB.");

    return messages;
  }
}
