package com.example.collabdocs.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class DocumentUpdateMessage {
    @NotNull
    private Long documentId;

    @NotNull
    @Size(min = 1)
    private String content;

    private Long version;

    public Long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
