package com.example.collabdocs.service;

import com.example.collabdocs.model.Document;
import com.example.collabdocs.repository.DocumentRepository;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DocumentService {

    private final DocumentRepository repository;

    public DocumentService(DocumentRepository repository) {
        this.repository = repository;
    }

    public List<Document> findAll() {
        return repository.findAll();
    }

    public Document get(Long id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Document not found: " + id));
    }

    public Document create(String title, String content) {
        Document doc = new Document(title, content);
        return repository.save(doc);
    }

    @Transactional
    public Document updateContent(Long id, String content, Long expectedVersion) {
        Document doc = get(id);
        if (expectedVersion != null && doc.getVersion() != null && !doc.getVersion().equals(expectedVersion)) {
            throw new OptimisticLockingFailureException("Document has newer version");
        }
        doc.setContent(content);
        return repository.save(doc);
    }
}
