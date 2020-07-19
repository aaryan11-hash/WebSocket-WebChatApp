package com.aaryan.WebsocketWebchat.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Getter
@Setter
public class ChatMessage {
    private MessageType type;

    private String content;

    private String sender;

    private String time;
}
