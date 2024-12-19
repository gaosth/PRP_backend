package com.zju.prp.controller;

import com.zju.prp.exception.ResourceNotFoundException;
import com.zju.prp.model.Projects;
import com.zju.prp.repository.ProjectsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<Projects> getAllProjects() {
        return this.projectsRepository.findAll();
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