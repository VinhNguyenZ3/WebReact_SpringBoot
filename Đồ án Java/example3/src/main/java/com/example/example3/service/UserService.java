package com.example.example3.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.example3.entity.User;

public interface UserService {
    User createUser(User user);

    User getUserById(Long userId);

    Page<User> getAllUsers(Pageable pageable);

    User updateUser(User user);

    void deleteUser(Long userId);
    // public void saveImage(MultipartFile file) throws IOException;
}
