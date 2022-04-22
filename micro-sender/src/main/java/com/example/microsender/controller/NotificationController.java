package com.example.microsender.controller;

import com.example.microsender.service.NotificationService;
import com.example.microsender.dto.NotificationDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class NotificationController {

  private final NotificationService notificationService;

  @PostMapping("/notification")
  public ResponseEntity<String> sendNotification(@RequestBody NotificationDto notificationDto) {
    log.info("Processing message '{}' from user {}.", notificationDto.getMessage(), notificationDto.getUser());
    var response = notificationService.sendNotification(notificationDto);
    return ResponseEntity.ok(response);
  }
}
