package com.example.microsender.service;

import com.example.microsender.dto.NotificationDto;

public interface NotificationService {

  String sendNotification(NotificationDto notificationDto);
}
