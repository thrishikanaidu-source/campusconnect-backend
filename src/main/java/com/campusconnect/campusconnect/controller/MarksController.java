package com.campusconnect.campusconnect.controller;

import com.campusconnect.campusconnect.model.Marks;
import com.campusconnect.campusconnect.repository.MarksRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/marks")
public class MarksController {

    private final MarksRepository repo;

    public MarksController(MarksRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Marks> getAll() {
        return repo.findAll();
    }

    @GetMapping("/student/{studentId}")
    public List<Marks> getByStudent(@PathVariable Long studentId) {
        return repo.findByStudentId(studentId);
    }

    @PostMapping
    public Marks add(@RequestBody Marks marks) {
        return repo.save(marks);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Marks> update(@PathVariable Long id, @RequestBody Marks updated) {
        return repo.findById(id).map(m -> {
            m.setStudentId(updated.getStudentId());
            m.setCourseId(updated.getCourseId());
            m.setExamType(updated.getExamType());
            m.setMarksObtained(updated.getMarksObtained());
            m.setMaxMarks(updated.getMaxMarks());
            return ResponseEntity.ok(repo.save(m));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
