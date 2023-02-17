package com.mrknight.rabbitdemo.services;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mrknight.rabbitdemo.configuration.Constants;
import org.springframework.amqp.core.AmqpTemplate;

@Service
public class QueuesService {
  @Autowired
  private AmqpTemplate rabbitTemplate;

  @RabbitListener(queues = Constants.TEST_QUEUE)
  public void listen(String in) {
    System.out.println("Message read from myQueue : " + in);
  }

  public String sendMsg(String msg) {
    rabbitTemplate.convertAndSend(Constants.TEST_QUEUE, msg);
    return "Send msg:: " + msg;
  }

}
