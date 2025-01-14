package com.zju.prp.controller;

import com.zju.prp.exception.ResourceNotFoundException;
import com.zju.prp.model.Projects;
import com.zju.prp.repository.ProjectsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/projects")
public class ProjectsController {

    private final ProjectsRepository projectsRepository;

    // 构造函数注入，无需 @Autowired 注解
    public ProjectsController(ProjectsRepository projectsRepository) {
        this.projectsRepository = projectsRepository;
    }

    // 获取所有项目
    @GetMapping("")
    public ResponseEntity<List<Projects>> getAllProjects() {
        return ResponseEntity.ok(this.projectsRepository.findAll());
    }

    @GetMapping("/list-simply")
    public ResponseEntity<List<Map<String, Object>>> getProjectsSimply() {
        List<Projects> projectsList = this.projectsRepository.findAll();
        // 将 Projects 对象转换为 Map 格式
        List<Map<String, Object>> result = projectsList.stream().map(project -> {
            Map<String, Object> map = new HashMap<>();
            map.put("project_id", project.getProjectId());
            map.put("title", project.getTitle());
            map.put("image_url", project.getImage() != null ? project.getImage().getImageUrl() : null);
            return map;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    // 根据 ID 获取单个项目
    @GetMapping("/{id}")
    public ResponseEntity<Projects> getProjectById(@PathVariable Integer id) {
        Projects project = this.projectsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));
        return ResponseEntity.ok(project);
    }

    // 创建新项目
    @PostMapping("")
    public ResponseEntity<Projects> createProject(@RequestBody Projects newProject) {
        Projects createdProject = this.projectsRepository.save(newProject);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProject);
    }

    // 更新项目信息
    @PutMapping("/{id}")
    public ResponseEntity<Projects> updateProject(@PathVariable Integer id, @RequestBody Projects projectDetails) {
        Projects existingProject = this.projectsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));

        existingProject.setTitle(projectDetails.getTitle());
        existingProject.setArtist(projectDetails.getArtist());
        existingProject.setEra(projectDetails.getEra());
        existingProject.setMaterial(projectDetails.getMaterial());
        existingProject.setSize(projectDetails.getSize());
        existingProject.setInstitution(projectDetails.getInstitution());
        existingProject.setDescription(projectDetails.getDescription());
        existingProject.setPoem(projectDetails.getPoem());
        existingProject.setImage(projectDetails.getImage());
        existingProject.setStatus(projectDetails.getStatus());
        existingProject.setUser(projectDetails.getUser());
        existingProject.setRef(projectDetails.getRef());

        Projects updatedProject = this.projectsRepository.save(existingProject);
        return ResponseEntity.ok(updatedProject);
    }

    // 删除项目
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Integer id) {
        Projects existingProject = this.projectsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));

        this.projectsRepository.delete(existingProject);
        return ResponseEntity.noContent().build();
    }
}