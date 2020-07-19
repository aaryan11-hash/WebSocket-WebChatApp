package com.aaryan.WebsocketWebchat.Controller;

import com.aaryan.WebsocketWebchat.model.ChatMessage;
import com.aaryan.WebsocketWebchat.model.MessageType;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@Slf4j
public class WebsocketEventListner {

    @Autowired
    private SimpMessageSendingOperations sendingOperations;

    @EventListener
    public void handleWebsocketConnectListner(final SessionConnectedEvent event){
    log.info("Bing bing bing. We have a new little Connection");
    }

    @EventListener
    public void handleWebSocketDisconnectListner(final SessionDisconnectEvent event){
        final StompHeaderAccessor headerAccessor=StompHeaderAccessor.wrap(event.getMessage());
        final String username=(String) headerAccessor.getSessionAttributes().get("username");
        final ChatMessage chatMessage=ChatMessage.builder().type(MessageType.DISCONNECT)
                .sender(username)
                .build();

        sendingOperations.convertAndSend("/topic/public",chatMessage);
    }

}
