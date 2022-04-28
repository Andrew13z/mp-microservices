package com.example.microsender.service.impl;

import com.example.microsender.dto.NotificationDto;
import com.example.microsender.publisher.NotificationPublisher;
import com.example.microsender.service.NotificationService;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Counter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

  private final NotificationPublisher notificationPublisher;

  private final Counter notificationsCounter;

  @Autowired
  public NotificationServiceImpl(NotificationPublisher notificationPublisher,
    CollectorRegistry collectorRegistry) {
    this.notificationPublisher = notificationPublisher;
    this.notificationsCounter = Counter.build()
                          .name("notifications_total")
                          .help("Total notifications.")
                          .register(collectorRegistry);
  }

  @Override
  public String sendNotification(NotificationDto notificationDto) {
    notificationsCounter.inc();
    return notificationPublisher.sendNotification(notificationDto.getMessage());
  }
}
