package com.cobanogluhasan.springboot.controller;

import com.cobanogluhasan.springboot.exception.ResourceNotFoundException;
import com.cobanogluhasan.springboot.model.StarProjects;
import com.cobanogluhasan.springboot.repository.StarProjectsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List; // 确保包含这个导入语句

@RestController
@CrossOrigin
@RequestMapping("/starprojects")
public class StarProjectsController {

    private final StarProjectsRepository starProjectsRepository;

    // 构造函数注入，无需 @Autowired 注解
    public StarProjectsController(StarProjectsRepository starProjectsRepository) {
        this.starProjectsRepository = starProjectsRepository;
    }

    // 获取所有标记项目
    @GetMapping("")
    public List<StarProjects> getAllStarProjects() {
        return this.starProjectsRepository.findAll();
    }

    // 根据 ID 获取单个标记项目
    @GetMapping("/{id}")
    public ResponseEntity<StarProjects> getStarProjectById(@PathVariable Integer id) {
        StarProjects starProject = this.starProjectsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Star Project not found with id: " + id));
        return ResponseEntity.ok(starProject);
    }

    // 创建新标记项目记录
    @PostMapping("")
    public ResponseEntity<StarProjects> createStarProject(@RequestBody StarProjects newStarProject) {
        StarProjects createdStarProject = this.starProjectsRepository.save(newStarProject);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStarProject);
    }

    // 删除标记项目记录
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStarProject(@PathVariable Integer id) {
        StarProjects existingStarProject = this.starProjectsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Star Project not found with id: " + id));

        this.starProjectsRepository.delete(existingStarProject);
        return ResponseEntity.noContent().build();
    }

    // 获取特定用户的标记项目列表
    @GetMapping("/user/{userId}")
    public List<StarProjects> getStarProjectsByUserId(@PathVariable Integer userId) {
        return this.starProjectsRepository.findByUserUserId(userId);
    }
}