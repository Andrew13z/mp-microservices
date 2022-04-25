package com.example.microrecipient.mapper;

import com.example.microrecipient.entity.Notification;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {

  public Notification fromString(String message) {
    var notification = new Notification();
    notification.setMessage(message);

    return notification;
  }
}
