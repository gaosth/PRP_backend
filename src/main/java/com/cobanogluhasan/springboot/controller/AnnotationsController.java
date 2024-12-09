package com.cobanogluhasan.springboot.controller;

import com.cobanogluhasan.springboot.exception.ResourceNotFoundException;
import com.cobanogluhasan.springboot.model.Annotations;
import com.cobanogluhasan.springboot.repository.AnnotationsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/annotations")
public class AnnotationsController {

    private final AnnotationsRepository annotationsRepository;

    // 构造函数注入，无需 @Autowired 注解
    public AnnotationsController(AnnotationsRepository annotationsRepository) {
        this.annotationsRepository = annotationsRepository;
    }

    // 获取所有标注
    @GetMapping("")
    public List<Annotations> getAllAnnotations() {
        return this.annotationsRepository.findAll();
    }

    // 根据 ID 获取单个标注
    @GetMapping("/{id}")
    public ResponseEntity<Annotations> getAnnotationById(@PathVariable Integer id) {
        Annotations annotation = this.annotationsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Annotation not found with id: " + id));
        return ResponseEntity.ok(annotation);
    }

    // 创建新标注
    @PostMapping("")
    public ResponseEntity<Annotations> createAnnotation(@RequestBody Annotations newAnnotation) {
        Annotations createdAnnotation = this.annotationsRepository.save(newAnnotation);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAnnotation);
    }

    // 更新标注信息
    @PutMapping("/{id}")
    public ResponseEntity<Annotations> updateAnnotation(@PathVariable Integer id, @RequestBody Annotations annotationDetails) {
        Annotations existingAnnotation = this.annotationsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Annotation not found with id: " + id));

        existingAnnotation.setProject(annotationDetails.getProject());
        existingAnnotation.setAxis(annotationDetails.getAxis());
        existingAnnotation.setMixMethod(annotationDetails.getMixMethod());

        Annotations updatedAnnotation = this.annotationsRepository.save(existingAnnotation);
        return ResponseEntity.ok(updatedAnnotation);
    }

    // 删除标注
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnnotation(@PathVariable Integer id) {
        Annotations existingAnnotation = this.annotationsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Annotation not found with id: " + id));

        this.annotationsRepository.delete(existingAnnotation);
        return ResponseEntity.noContent().build();
    }
}