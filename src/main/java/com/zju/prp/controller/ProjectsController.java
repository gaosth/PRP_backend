package com.zju.prp.controller;

import com.zju.prp.exception.ResourceNotFoundException;
import com.zju.prp.model.Projects;
import com.zju.prp.model.Users;
import com.zju.prp.model.UploadImages;
import com.zju.prp.model.dto.ProjectsRequest;
import com.zju.prp.repository.ProjectsRepository;
import com.zju.prp.repository.UploadImagesRepository;
import com.zju.prp.repository.UsersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/projects")
public class ProjectsController {

    private final ProjectsRepository projectsRepository;
    private final UsersRepository usersRepository;
    private final UploadImagesRepository uploadImagesRepository;

    // 构造函数注入，无需 @Autowired 注解
    public ProjectsController(ProjectsRepository projectsRepository,
                              UsersRepository usersRepository,
                              UploadImagesRepository uploadImagesRepository) {
        this.projectsRepository = projectsRepository;
        this.usersRepository = usersRepository;
        this.uploadImagesRepository = uploadImagesRepository;
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

    @PostMapping("")
    public ResponseEntity<Projects> createProject(@RequestBody Projects newProject) {
        Projects createdProject = this.projectsRepository.save(newProject);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProject);
    }

    /**
     * 创建新项目 暂不启用
     */
//    @PostMapping("")
//    public ResponseEntity<Projects> createProject(@Valid @RequestBody ProjectsRequest request) {
//        try {
//            // 创建一个新的 Projects 实体
//            Projects newProject = new Projects();
//
//            // 设置项目属性
//            newProject.setTitle(request.getTitle());
//            newProject.setArtist(request.getArtist());
//            newProject.setEra(request.getEra());
//            newProject.setMaterial(request.getMaterial());
//            newProject.setSize(request.getSize());
//            newProject.setInstitution(request.getInstitution());
//            newProject.setDescription(request.getDescription());
//            newProject.setPoem(request.getPoem());
//            newProject.setStatus(request.getStatus());
//            newProject.setRef(request.getRef());
//            newProject.setPoemAuthor(request.getPoemAuthor());
//            newProject.setBackground(request.getBackground());
//            newProject.setAllusion(request.getAllusion());
//
//            // 验证并获取用户信息
//            Users user = this.usersRepository.findById(request.getUserId())
//                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + request.getUserId()));
//            newProject.setUser(user);
//
//            // 如果提供了 Image_ID，则验证并获取对应的 UploadImages 实体
//            UploadImages image = this.uploadImagesRepository.findById(request.getImageId())
//                .orElseThrow(() -> new ResourceNotFoundException("Image not found with id: " + request.getImageId()));
//            newProject.setImage(image);
//
//            // 保存新创建的项目
//            Projects createdProject = this.projectsRepository.save(newProject);
//
//            return ResponseEntity.status(HttpStatus.CREATED).body(createdProject);
//        } catch (ResourceNotFoundException e) {
//            // 处理资源未找到异常
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//        } catch (Exception e) {
//            // 处理其他异常
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//        }
//    }

    // 更新项目信息

    @PutMapping("/{id}")
    public ResponseEntity<Projects> updateProject(@PathVariable Integer id, @Valid @RequestBody ProjectsRequest request) {
        Projects existingProject = this.projectsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));

        // 更新项目属性
        existingProject.setTitle(request.getTitle());
        existingProject.setArtist(request.getArtist());
        existingProject.setEra(request.getEra());
        existingProject.setMaterial(request.getMaterial());
        existingProject.setSize(request.getSize());
        existingProject.setInstitution(request.getInstitution());
        existingProject.setDescription(request.getDescription());
        existingProject.setPoem(request.getPoem());
        existingProject.setStatus(request.getStatus());
        existingProject.setRef(request.getRef());
        existingProject.setPoemAuthor(request.getPoemAuthor());

        // 更新新增字段
        existingProject.setBackground(request.getBackground());
        existingProject.setAllusion(request.getAllusion());

        // 验证并获取用户信息
        Users user = this.usersRepository.findById(request.getUserId())
            .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + request.getUserId()));
        existingProject.setUser(user);

        // 验证并获取上传的图片信息
        UploadImages image = this.uploadImagesRepository.findById(request.getImageId())
            .orElseThrow(() -> new ResourceNotFoundException("Image not found with id: " + request.getImageId()));
        existingProject.setImage(image);

        // 保存更新后的项目
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