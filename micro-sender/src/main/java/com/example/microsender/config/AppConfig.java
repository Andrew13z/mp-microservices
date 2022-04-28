package com.example.microsender.config;

import io.prometheus.client.CollectorRegistry;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

  @Value("${app.rabbitmq.queue.name}")
  private String queueName;

  @Value("${app.rabbitmq.topic.exchange.name}")
  private String topicExchangeName;

  @Value("${app.rabbitmq.routing.key}")
  private String routingKey;

  @Bean
  public Queue queue() {
    return new Queue(queueName, false);
  }

  @Bean
  public TopicExchange topicExchange() {
    return new TopicExchange(topicExchangeName);
  }

  @Bean
  public Binding binding(Queue queue, TopicExchange exchange) {
    return BindingBuilder.bind(queue).to(exchange).with(routingKey);
  }

  @Bean
  public CollectorRegistry collectorRegistry() {
    return CollectorRegistry.defaultRegistry;
  }
}
