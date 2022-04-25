package com.example.microrecipient.controller;

import com.example.microrecipient.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class NotificationController {

  private final NotificationService notificationService;

  @GetMapping("/message")
  public List<String> getAndDeleteAllMessages() {
    return notificationService.getAndDeleteAllMessages();
  }
}
