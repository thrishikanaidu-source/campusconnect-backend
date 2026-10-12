package com.campusconnect.campusconnect.controller;

import com.campusconnect.campusconnect.model.Attendance;
import com.campusconnect.campusconnect.repository.AttendanceRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    private final AttendanceRepository repo;

    public AttendanceController(AttendanceRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Attendance> getAll() {
        return repo.findAll();
    }

    @GetMapping("/student/{studentId}")
    public List<Attendance> getByStudent(@PathVariable Long studentId) {
        return repo.findByStudentId(studentId);
    }

    @GetMapping("/student/{studentId}/percentage")
    public double getPercentage(@PathVariable Long studentId) {
        List<Attendance> records = repo.findByStudentId(studentId);
        if (records.isEmpty()) {
            return 0;
        }
        long present = records.stream()
                .filter(a -> "PRESENT".equalsIgnoreCase(a.getStatus()))
                .count();
        return (present * 100.0) / records.size();
    }

    @PostMapping
    public Attendance add(@RequestBody Attendance attendance) {
        Optional<Attendance> existing = repo.findByStudentIdAndCourseIdAndDate(
                attendance.getStudentId(), attendance.getCourseId(), attendance.getDate());
        if (existing.isPresent()) {
            Attendance a = existing.get();
            a.setStatus(attendance.getStatus());
            return repo.save(a);
        }
        return repo.save(attendance);
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
