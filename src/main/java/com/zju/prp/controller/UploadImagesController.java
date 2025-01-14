package com.zju.prp.controller;

import com.zju.prp.exception.ResourceNotFoundException;
import com.zju.prp.model.Result;
import com.zju.prp.model.UploadImages;
import com.zju.prp.model.Users;
import com.zju.prp.repository.UploadImagesRepository;
import com.zju.prp.repository.UsersRepository;
import com.zju.prp.service.MinioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/uploadimages")
public class UploadImagesController {

    private final UploadImagesRepository uploadImagesRepository;
    private final UsersRepository usersRepository;
    private final MinioService minioService;

    // 构造函数注入，无需 @Autowired 注解
    public UploadImagesController(UploadImagesRepository uploadImagesRepository, UsersRepository usersRepository, MinioService minioService) {
        this.uploadImagesRepository = uploadImagesRepository;
        this.usersRepository = usersRepository;
        this.minioService = minioService;
    }

    // 获取所有上传图片
    @GetMapping("")
    public List<UploadImages> getAllUploadImages() {
        return this.uploadImagesRepository.findAll();
    }

    // 根据ID获取上传图片
    @GetMapping("/{imageId}")
    public ResponseEntity<UploadImages> getUploadImageById(@PathVariable Integer imageId) {
        UploadImages uploadImage = this.uploadImagesRepository.findById(imageId)
                .orElseThrow(() -> new ResourceNotFoundException("UploadImage not found with id: " + imageId));
        return ResponseEntity.ok(uploadImage);
    }

    // 创建新上传图片记录
    @PostMapping("/upload")
    public ResponseEntity<UploadImages> uploadImage(@RequestParam("file") MultipartFile file, @RequestParam("userId") Integer userId) {
        try {
            // 通过userId查找用户信息
            Users user = this.usersRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

            // 上传文件到MinIO，并获取返回的对象ID
            String minioId = minioService.uploadFileToMinioUnsafe(file);

            // 创建新的UploadImages实例
            UploadImages newUploadImage = new UploadImages(minioId, user);
            UploadImages savedUploadImage = this.uploadImagesRepository.save(newUploadImage);

            return ResponseEntity.status(HttpStatus.CREATED).body(savedUploadImage);
        } catch (Exception e) {
            // 处理异常
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // 更新上传图片信息
    @PutMapping("/{imageId}")
    public ResponseEntity<UploadImages> updateUploadImage(@PathVariable Integer imageId, @RequestBody UploadImages uploadImageDetails) {
        UploadImages existingUploadImage = this.uploadImagesRepository.findById(imageId)
                .orElseThrow(() -> new ResourceNotFoundException("UploadImage not found with id: " + imageId));

        if (uploadImageDetails.getMinioId() != null && !uploadImageDetails.getMinioId().isEmpty()) {
            existingUploadImage.setMinioId(uploadImageDetails.getMinioId());
        }
        if (uploadImageDetails.getUser() != null) {
            existingUploadImage.setUser(uploadImageDetails.getUser());
        }

        UploadImages updatedUploadImage = this.uploadImagesRepository.save(existingUploadImage);
        return ResponseEntity.ok(updatedUploadImage);
    }

    // 删除上传图片记录
    @DeleteMapping("/{imageId}")
    public ResponseEntity<Void> deleteUploadImage(@PathVariable Integer imageId) {
        UploadImages existingUploadImage = this.uploadImagesRepository.findById(imageId)
                .orElseThrow(() -> new ResourceNotFoundException("UploadImage not found with id: " + imageId));

        this.uploadImagesRepository.delete(existingUploadImage);
        return ResponseEntity.noContent().build();
    }
}