package com.example.microrecipient.repository;

import com.example.microrecipient.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

  @Query(value = "select min(n.id) from Notification n")
  Optional<Long> getMinId();
}
