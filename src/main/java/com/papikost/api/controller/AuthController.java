package com.papikost.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");

        Map<String, Object> kingEmyuResponse = new HashMap<>();

        if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
            kingEmyuResponse.put("success", true);
            kingEmyuResponse.put("message", "Login berhasil");

            Map<String, Object> userData = new HashMap<>();
            userData.put("id", 1);
            userData.put("username", username);
            userData.put("role", "pencari"); 

            kingEmyuResponse.put("user", userData);
            return ResponseEntity.ok(kingEmyuResponse);
        } else {
            kingEmyuResponse.put("success", false);
            kingEmyuResponse.put("error", "Username atau password tidak boleh kosong");
            return ResponseEntity.badRequest().body(kingEmyuResponse);
        }
    }
}