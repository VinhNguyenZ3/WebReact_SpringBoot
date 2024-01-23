package com.example.example3.controller;

import lombok.AllArgsConstructor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.example3.entity.User;
import com.example.example3.service.UserService;

@RestController
@AllArgsConstructor
@RequestMapping("api/users")
@CrossOrigin(origins = "*", exposedHeaders = "Content-Range")
public class UserController {

    private UserService userService;

    // Create user REST API
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User User) {
        User.setPhoto(User.getPhoto() + ".png");
        User savedUser = userService.createUser(User);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PostMapping("/image")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file,
            @RequestParam("customName") String customName) {
        try {
            // Save the uploaded image to a database or a file system
            String uploadDir = "src/main/resources/static/dataImage"; // Set your desired directory path

            // Create the directory if it doesn't exist
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Generate a unique filename for the uploaded image (you may need to modify
            // this logic)
            // String fileName = file.getOriginalFilename();

            String filePath = uploadDir + File.separator + customName + ".png";

            // Save the uploaded image to the specified directory
            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                fos.write(file.getBytes());
            }
            return ResponseEntity.ok("Image uploaded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image");
        }
    }

    @GetMapping("/image/{imageName}")
    public ResponseEntity<byte[]> getImage(@PathVariable String imageName) throws IOException {
        // You should sanitize and validate the imageName parameter to prevent directory
        // traversal attacks.
        // In this example, it is assumed that imageName is just the name of the image
        // file.

        Resource resource = new ClassPathResource("static/dataImage/" + imageName);
        byte[] imageBytes = Files.readAllBytes(resource.getFile().toPath());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG); // Set the appropriate image media type

        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }

    // Get user by id REST API
    // http://localhost:8080/api/users/1
    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long UserId) {
        User User = userService.getUserById(UserId);
        return new ResponseEntity<>(User, HttpStatus.OK);
    }

    // Get All users REST API
    // http://localhost:8080/api/users

    @GetMapping
    public ResponseEntity<Page<User>> getAllUsers(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> userPage = userService.getAllUsers(pageable);

        return new ResponseEntity<>(userPage, HttpStatus.OK);
    }

    // Update user REST API
    @PutMapping("{id}")
    // http://localhost:8080/api/users/1
    public ResponseEntity<User> updateUser(@PathVariable("id") Long UserId,
            @RequestBody User User) {
        User.setId(UserId);
        User updatedUser = userService.updateUser(User);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    // Delete User REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long UserId) {
        userService.deleteUser(UserId);
        return new ResponseEntity<>("User successfully deleted!", HttpStatus.OK);
    }
}
