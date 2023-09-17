package com.mrknight.rabbitdemo.services;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mrknight.rabbitdemo.configuration.Constants;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.TopicExchange;

@Service
public class QueuesService {
  @Autowired
  private AmqpTemplate rabbitTemplate;

  @Autowired
  private TopicExchange topic;

  @RabbitListener(queues = Constants.TEST_QUEUE)
  public void receiveTestQueue(String in) {
    System.out.println("Message read from myQueue : " + in);
  }

  @RabbitListener(queues = Constants.LALIGA_MARCADORES_QUEUE)
  public void receiveMarcadoresLaLiga(String msg) {
    System.out.println("Message read from " + Constants.LALIGA_MARCADORES_QUEUE + " : " + msg);
  }

  public String sendTestMsg(String msg) {
    rabbitTemplate.convertAndSend(Constants.TEST_QUEUE, msg);
    return "Send msg:: " + msg;
  }

  public String sendMarcadorMsg(String msg) {
    rabbitTemplate.convertAndSend(topic.getName(), Constants.LALIGA_MARCADORES_EXC_KEY, msg);
    return "Send msg:: " + msg;
  }

}
