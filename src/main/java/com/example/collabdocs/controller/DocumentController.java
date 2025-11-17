package com.example.collabdocs.controller;

import com.example.collabdocs.dto.DocumentSnapshot;
import com.example.collabdocs.model.Document;
import com.example.collabdocs.service.DocumentService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Validated
@Controller
public class DocumentController {

    private final DocumentService service;

    public DocumentController(DocumentService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Document> docs = service.findAll();
        model.addAttribute("documents", docs);
        return "index";
    }

    @GetMapping("/documents/{id}")
    public String editor(@PathVariable Long id, Model model, Principal principal) {
        Document doc = service.get(id);
        model.addAttribute("doc", doc);
        model.addAttribute("username", principal.getName());
        return "editor";
    }

    @PostMapping("/documents")
    public String createDocument(@RequestParam @NotBlank @Size(max = 128) String title) {
        service.create(title, "Document nou");
        return "redirect:/";
    }

    @GetMapping("/api/documents/{id}")
    @ResponseBody
    public DocumentSnapshot apiGetDocument(@PathVariable Long id) {
        return DocumentSnapshot.from(service.get(id));
    }

    @GetMapping("/api/documents")
    @ResponseBody
    public List<DocumentSnapshot> apiList() {
        return service.findAll().stream().map(DocumentSnapshot::from).toList();
    }

    @PostMapping("/api/documents")
    public ResponseEntity<DocumentSnapshot> apiCreate(@RequestParam String title) {
        Document created = service.create(title, "");
        return ResponseEntity.ok(DocumentSnapshot.from(created));
    }
}
