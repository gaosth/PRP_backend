package com.cobanogluhasan.springboot.controller;

import com.cobanogluhasan.springboot.exception.ResourceNotFoundException;
import com.cobanogluhasan.springboot.model.Photos;
import com.cobanogluhasan.springboot.repository.PhotosRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/photos")
public class PhotosController {

    private final PhotosRepository photosRepository;

    // 构造函数注入，无需 @Autowired 注解
    public PhotosController(PhotosRepository photosRepository) {
        this.photosRepository = photosRepository;
    }

    // 获取所有照片
    @GetMapping("")
    public List<Photos> getAllPhotos() {
        return this.photosRepository.findAll();
    }

    // 根据 ID 获取单个照片
    @GetMapping("/{id}")
    public ResponseEntity<Photos> getPhotoById(@PathVariable Integer id) {
        Photos photo = this.photosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Photo not found with id: " + id));
        return ResponseEntity.ok(photo);
    }

    // 创建新照片记录
    @PostMapping("")
    public ResponseEntity<Photos> createPhoto(@RequestBody Photos newPhoto) {
        Photos createdPhoto = this.photosRepository.save(newPhoto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPhoto);
    }

    // 更新照片信息
    @PutMapping("/{id}")
    public ResponseEntity<Photos> updatePhoto(@PathVariable Integer id, @RequestBody Photos photoDetails) {
        Photos existingPhoto = this.photosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Photo not found with id: " + id));

        existingPhoto.setSpecies(photoDetails.getSpecies());
        existingPhoto.setMinioId(photoDetails.getMinioId());

        Photos updatedPhoto = this.photosRepository.save(existingPhoto);
        return ResponseEntity.ok(updatedPhoto);
    }

    // 删除照片记录
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePhoto(@PathVariable Integer id) {
        Photos existingPhoto = this.photosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Photo not found with id: " + id));

        this.photosRepository.delete(existingPhoto);
        return ResponseEntity.noContent().build();
    }
}