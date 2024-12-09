package com.cobanogluhasan.springboot.controller;

import com.cobanogluhasan.springboot.exception.ResourceNotFoundException;
import com.cobanogluhasan.springboot.model.UploadImages;
import com.cobanogluhasan.springboot.repository.UploadImagesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/uploadimages")
public class UploadImagesController {

    private final UploadImagesRepository uploadImagesRepository;

    // 构造函数注入，无需 @Autowired 注解
    public UploadImagesController(UploadImagesRepository uploadImagesRepository) {
        this.uploadImagesRepository = uploadImagesRepository;
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
    @PostMapping("")
    public ResponseEntity<UploadImages> createUploadImage(@RequestBody UploadImages newUploadImage) {
        UploadImages createdUploadImage = this.uploadImagesRepository.save(newUploadImage);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUploadImage);
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