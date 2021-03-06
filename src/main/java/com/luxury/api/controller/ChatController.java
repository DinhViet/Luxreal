package com.luxury.api.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.luxury.model.Message;
import com.luxury.model.OutputMessage;

import org.springframework.messaging.handler.annotation.DestinationVariable;

@Controller
public class ChatController 
{
    @MessageMapping("/chat/{topic}")
    @SendTo("/topic/messages")
    public OutputMessage send(@DestinationVariable("topic") String topic,
			      Message message) throws Exception
    {
	return new OutputMessage(message.getFrom(), message.getText(), topic);
    }
}
