package com.example.microsender.service.impl;

import com.example.microsender.dto.NotificationDto;
import com.example.microsender.producer.NotificationPublisher;
import com.example.microsender.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

  private final NotificationPublisher notificationPublisher;

  @Override
  public String sendNotification(NotificationDto notificationDto) {
    return notificationPublisher.sendNotification(notificationDto.getMessage());
  }
}
