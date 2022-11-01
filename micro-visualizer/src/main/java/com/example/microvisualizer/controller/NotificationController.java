package com.example.microvisualizer.controller;

import com.example.microvisualizer.service.NotificationService;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Counter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/saved-messages")
public class NotificationController {

  private final NotificationService notificationService;

  private final Counter reqeustCounter;

  @Autowired
  public NotificationController(
    NotificationService notificationService,
    CollectorRegistry collectorRegistry) {
    this.notificationService = notificationService;
    this.reqeustCounter = Counter.build()
      .name("visualize_request_count")
      .help("Request count to the visualizer service.")
      .register(collectorRegistry);
  }

  @GetMapping
  public Page<String> getNotifications(Pageable pageable) {
    reqeustCounter.inc();
    return notificationService.getNotifications(pageable);
  }
}
