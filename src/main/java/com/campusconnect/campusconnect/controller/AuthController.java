package com.campusconnect.campusconnect.controller;

import com.campusconnect.campusconnect.model.User;
import com.campusconnect.campusconnect.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository repo;

    public AuthController(UserRepository repo) {
        this.repo = repo;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");

        Optional<User> found = repo.findByUsername(username);
        if (found.isPresent() && found.get().getPassword().equals(password)) {
            User u = found.get();
            Map<String, Object> response = new HashMap<>();
            response.put("id", u.getId());
            response.put("username", u.getUsername());
            response.put("role", u.getRole());
            response.put("linkedId", u.getLinkedId());
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(401).body(Map.of("message", "Invalid username or password"));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (repo.findByUsername(user.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body(Map.of("message", "Username already exists"));
        }
        return ResponseEntity.ok(repo.save(user));
    }
}
