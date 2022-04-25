package com.example.microcollector.scheduler;

import com.example.microcollector.client.MicroRecipientClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class MessageScheduler {

  private final MicroRecipientClient client;

  @Scheduled(fixedDelay = 20_000L)
  public void getAndLogMessages() {
    var allMessages = client.getAllMessages();
    log.info("Received {} message(s) from micro-recipient service: {}.", allMessages.size(), allMessages);
  }
}
