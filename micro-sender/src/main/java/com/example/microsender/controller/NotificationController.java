package com.example.microsender.controller;

import com.example.microsender.dto.NotificationDto;
import com.example.microsender.service.NotificationService;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Histogram;
import io.prometheus.client.Summary;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class NotificationController {

  private final Histogram requestLatency;

  private final Summary receivedLettersNumber;

  private final NotificationService notificationService;

  @Autowired
  public NotificationController(
    NotificationService notificationService,
    CollectorRegistry collectorRegistry) {
    this.notificationService = notificationService;
    this.requestLatency = Histogram.build()
      .buckets(0.1, 0.5, 1.0, 5.0)
      .name("request_latency_seconds")
      .help("Request latency in seconds.")
      .register(collectorRegistry);
    this.receivedLettersNumber = Summary.build()
      .name("notification_letter_number")
      .help("Number of all letters in notifications.")
      .register(collectorRegistry);
  }

  @PostMapping("/notification")
  public ResponseEntity<String> sendNotification(@RequestBody NotificationDto notificationDto) {
    var timer = requestLatency.startTimer();
    String response;

    try {
      log.info("Processing message '{}' from user {}.",
        notificationDto.getMessage(),
        notificationDto.getUser());
      response = notificationService.sendNotification(notificationDto);
    } finally {
      timer.observeDuration();
      receivedLettersNumber.observe(notificationDto.getMessage().length());
    }

    return ResponseEntity.ok(response);
  }
}
