package com.example.collabdocs.controller;

import com.example.collabdocs.dto.CreateDocumentRequest;
import com.example.collabdocs.dto.DocumentSnapshot;
import com.example.collabdocs.model.Document;
import com.example.collabdocs.service.DocumentService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    private final DocumentService service;

    public DocumentController(DocumentService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public DocumentSnapshot apiGetDocument(@PathVariable Long id) {
        return DocumentSnapshot.from(service.get(id));
    }

    @GetMapping
    public List<DocumentSnapshot> apiList() {
        return service.findAll().stream().map(DocumentSnapshot::from).toList();
    }

    @PostMapping
    public ResponseEntity<DocumentSnapshot> apiCreate(@RequestBody @Validated CreateDocumentRequest request) {
        Document created = service.create(request.getTitle(), "");
        return ResponseEntity.ok(DocumentSnapshot.from(created));
    }
}
