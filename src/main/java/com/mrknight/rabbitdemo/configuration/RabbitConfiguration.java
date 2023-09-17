package com.mrknight.rabbitdemo.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {

  @Bean
  public Queue myQueue() {
    return new Queue(Constants.TEST_QUEUE, false);
  }

  @Bean
  public Queue marcadoresLaLigaQueue() {
    return new Queue(Constants.LALIGA_MARCADORES_QUEUE, true);
  }

  @Bean
  public TopicExchange jornadaLaLigaExchange() {
    return new TopicExchange(Constants.LALIGA_EXCHANGE);
  }

  @Bean
  public Binding marcadoresLaLigaBind(Queue marcadoresLaLigaQueue, TopicExchange jornadaLaLigaExchange) {
    return BindingBuilder.bind(marcadoresLaLigaQueue).to(jornadaLaLigaExchange).with(Constants.LALIGA_MARCADORES_KEY);
  }

  @Bean
  MessageConverter messageConverter() {
    return new Jackson2JsonMessageConverter();
  }
}