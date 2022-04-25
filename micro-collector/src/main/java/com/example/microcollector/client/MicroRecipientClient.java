package com.example.microcollector.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient("microrecipient")
public interface MicroRecipientClient {

  @GetMapping("/message")
  List<String> getAllMessages();
}
