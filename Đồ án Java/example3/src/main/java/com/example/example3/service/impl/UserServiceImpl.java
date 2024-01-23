package com.example.example3.service.impl;

import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.example3.entity.User;
import com.example.example3.service.UserService;
import com.example.example3.repository.UserRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.get();
    }

    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User updateUser(User user) {
        User existingUser = userRepository.findById(user.getId()).get();
        existingUser.setName(user.getName());
        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(user.getPassword());
        existingUser.setGender(user.getGender());
        existingUser.setPhone(user.getPhone());
        existingUser.setEmail(user.getEmail());
        existingUser.setPhoto(user.getPhoto());
        existingUser.setRole(user.getRole());
        existingUser.setStatus(user.getStatus());
        User updatedUser = userRepository.save(existingUser);
        return updatedUser;
    }
    // CategoryRepository categoryRepository;
    // @Override
    // public void saveImage(MultipartFile file) throws IOException {
    // User image = new User();
    // image.setPhotoData(file.getBytes());
    // Optional<Category> categoryOptional = categoryRepository.findById(1L);
    // Category category = categoryOptional.get();
    // image.setCategory(category);
    // image.setDescription("AA");
    // image.setPhoto("aaa");
    // image.setPrice(0);
    // image.setTitle("ANV");
    // userRepository.save(image);
    // }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
