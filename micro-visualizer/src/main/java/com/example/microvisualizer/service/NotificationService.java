package com.example.microvisualizer.service;

import com.example.microvisualizer.entity.Notification;
import com.example.microvisualizer.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

  private final NotificationRepository notificationRepository;

  public Page<String> getNotifications(Pageable pageable) {
    return notificationRepository.findAll(pageable)
      .map(Notification::getMessage);
  }
}
