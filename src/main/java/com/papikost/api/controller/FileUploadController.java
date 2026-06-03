package com.papikost.api.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/upload")
public class FileUploadController {

    @Value("${app.upload.dir:./uploads}")
    private String uploadDir;

    /**
     * Upload satu file (gambar / dokumen).
     * Multipart form-data dengan key "file".
     *
     * Request : POST /api/upload  (form-data, key="file")
     * Response: { success: true, url: "/uploads/uuid-namafile.jpg" }
     *
     * File disimpan di ./uploads/ (relatif terhadap root project backend).
     * Folder otomatis dibuat jika belum ada.
     * File dapat diakses secara publik via http://localhost:8080/uploads/...
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> uploadFile(
            @RequestParam("file") MultipartFile file) {

        Map<String, Object> response = new HashMap<>();

        // Validasi file tidak kosong
        if (file.isEmpty()) {
            response.put("success", false);
            response.put("error", "File tidak boleh kosong.");
            return ResponseEntity.badRequest().body(response);
        }

        // Validasi tipe file (hanya gambar & PDF)
        String contentType = file.getContentType();
        if (contentType == null || (!contentType.startsWith("image/") && !contentType.equals("application/pdf"))) {
            response.put("success", false);
            response.put("error", "Tipe file tidak didukung. Hanya gambar (JPG/PNG) dan PDF yang diterima.");
            return ResponseEntity.badRequest().body(response);
        }

        try {
            // Buat folder uploads jika belum ada
            Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
            Files.createDirectories(uploadPath);

            // Buat nama file unik: uuid + nama asli (dibersihkan)
            String originalName = file.getOriginalFilename();
            String safeName = originalName != null
                    ? originalName.replaceAll("[^a-zA-Z0-9.\\-_]", "_")
                    : "file";
            String uniqueFileName = UUID.randomUUID().toString().substring(0, 8) + "-" + safeName;

            // Salin file ke folder uploads
            Path targetPath = uploadPath.resolve(uniqueFileName);
            Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

            // URL publik yang bisa dirender di browser/img tag
            String publicUrl = "/uploads/" + uniqueFileName;

            response.put("success", true);
            response.put("url", publicUrl);
            response.put("fileName", uniqueFileName);
            response.put("originalName", originalName);
            response.put("size", file.getSize());

            return ResponseEntity.ok(response);

        } catch (IOException e) {
            response.put("success", false);
            response.put("error", "Gagal menyimpan file: " + e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }

    /**
     * Upload multiple files sekaligus (untuk foto kamar, maks 3).
     * Multipart form-data dengan key "files".
     *
     * Request : POST /api/upload/multiple  (form-data, key="files", multiple)
     * Response: { success: true, urls: ["/uploads/a.jpg", "/uploads/b.jpg"] }
     */
    @PostMapping("/multiple")
    public ResponseEntity<Map<String, Object>> uploadMultiple(
            @RequestParam("files") MultipartFile[] files) {

        Map<String, Object> response = new HashMap<>();

        if (files == null || files.length == 0) {
            response.put("success", false);
            response.put("error", "Tidak ada file yang dikirim.");
            return ResponseEntity.badRequest().body(response);
        }

        if (files.length > 3) {
            response.put("success", false);
            response.put("error", "Maksimal 3 file yang bisa diupload sekaligus.");
            return ResponseEntity.badRequest().body(response);
        }

        try {
            Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
            Files.createDirectories(uploadPath);

            java.util.List<String> urls = new java.util.ArrayList<>();

            for (MultipartFile file : files) {
                if (file.isEmpty()) continue;

                String originalName = file.getOriginalFilename();
                String safeName = originalName != null
                        ? originalName.replaceAll("[^a-zA-Z0-9.\\-_]", "_")
                        : "file";
                String uniqueFileName = UUID.randomUUID().toString().substring(0, 8) + "-" + safeName;

                Path targetPath = uploadPath.resolve(uniqueFileName);
                Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

                urls.add("/uploads/" + uniqueFileName);
            }

            response.put("success", true);
            response.put("urls", urls);
            // Juga kirim sebagai comma-separated string untuk langsung disimpan ke fotoUrls
            response.put("fotoUrlsString", String.join(",", urls));

            return ResponseEntity.ok(response);

        } catch (IOException e) {
            response.put("success", false);
            response.put("error", "Gagal menyimpan file: " + e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }
}
