package com.geekbrains.springmarket.controllers;

import com.geekbrains.springmarket.entites.Greeting;
import com.geekbrains.springmarket.entites.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ShopControllerWs {
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(Message message) throws Exception {
        Thread.sleep(2000);
        return new Greeting(message.getName() + " добавлен в корзину!");
    }
}
