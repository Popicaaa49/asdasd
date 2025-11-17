package com.example.collabdocs.controller;

import com.example.collabdocs.dto.DocumentSnapshot;
import com.example.collabdocs.dto.DocumentUpdateMessage;
import com.example.collabdocs.model.Document;
import com.example.collabdocs.service.DocumentService;
import jakarta.validation.Valid;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class DocumentWebSocketController {

    private final DocumentService service;
    private final SimpMessagingTemplate messagingTemplate;

    public DocumentWebSocketController(DocumentService service, SimpMessagingTemplate messagingTemplate) {
        this.service = service;
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/documents.update")
    public void handleUpdate(@Valid @Payload DocumentUpdateMessage message) {
        Document updated = service.updateContent(message.getDocumentId(), message.getContent(), message.getVersion());
        DocumentSnapshot snapshot = DocumentSnapshot.from(updated);
        // Broadcast to all subscribers of this document
        messagingTemplate.convertAndSend("/topic/documents/" + updated.getId(), snapshot);
    }
}
