package com.example.collabdocs.dto;

import com.example.collabdocs.model.Document;

import java.time.Instant;

public record DocumentSnapshot(Long id, String title, String content, Instant updatedAt, Long version) {
    public static DocumentSnapshot from(Document doc) {
        return new DocumentSnapshot(doc.getId(), doc.getTitle(), doc.getContent(), doc.getUpdatedAt(), doc.getVersion());
    }
}
