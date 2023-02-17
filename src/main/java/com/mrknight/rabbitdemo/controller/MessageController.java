package com.mrknight.rabbitdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mrknight.rabbitdemo.services.QueuesService;

@RestController
@RequestMapping("message")
public class MessageController {

  @Autowired
  QueuesService service;

  @PostMapping("/")
  String sendMessage(@RequestBody String msg) {
    return service.sendMsg(msg);
  }

}
