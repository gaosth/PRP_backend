package com.zju.prp.controller;

import com.zju.prp.exception.ResourceNotFoundException;
import com.zju.prp.model.Users;
import com.zju.prp.repository.UsersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UsersController {

    private final UsersRepository usersRepository;

    // 构造函数注入，无需 @Autowired 注解
    public UsersController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    // 获取所有用户
    @GetMapping("")
    public List<Users> getAllUsers() {
        return this.usersRepository.findAll();
    }

    // 根据ID获取用户
    @GetMapping("/{userId}")
    public ResponseEntity<Users> getUserById(@PathVariable Integer userId) {
        Users user = this.usersRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        return ResponseEntity.ok(user);
    }

    // 创建新用户
    @PostMapping("")
    public ResponseEntity<Users> createUser(@RequestBody Users newUser) throws NoSuchAlgorithmException {
        if (newUser.getPassword() != null && !newUser.getPassword().isEmpty()) {
            newUser.setPassword(newUser.getPassword());
        }
        Users createdUser = this.usersRepository.save(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    // 更新用户信息
    @PutMapping("/{userId}")
    public ResponseEntity<Users> updateUser(@PathVariable Integer userId, @RequestBody Users userDetails) throws NoSuchAlgorithmException {
        Users existingUser = this.usersRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        if (userDetails.getUserType() != null) {
            existingUser.setUserType(userDetails.getUserType());
        }
        if (userDetails.getName() != null) {
            existingUser.setName(userDetails.getName());
        }
        if (userDetails.getEmail() != null) {
            existingUser.setEmail(userDetails.getEmail());
        }
        if (userDetails.getPassword() != null && !userDetails.getPassword().isEmpty()) {
            existingUser.setPassword(userDetails.getPassword());
        }
        if (userDetails.getAvatar() != null) {
            existingUser.setAvatar(userDetails.getAvatar());
        }

        Users updatedUser = this.usersRepository.save(existingUser);
        return ResponseEntity.ok(updatedUser);
    }

    // 删除用户
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer userId) {
        Users existingUser = this.usersRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        this.usersRepository.delete(existingUser);
        return ResponseEntity.noContent().build();
    }
}