package com.cobanogluhasan.springboot.controller;

import com.cobanogluhasan.springboot.exception.ResourceNotFoundException;
import com.cobanogluhasan.springboot.model.PoemQuery;
import com.cobanogluhasan.springboot.repository.PoemQueryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/poemqueries")
public class PoemQueryController {

    private final PoemQueryRepository poemQueryRepository;

    // 构造函数注入，无需 @Autowired 注解
    public PoemQueryController(PoemQueryRepository poemQueryRepository) {
        this.poemQueryRepository = poemQueryRepository;
    }

    // 获取所有诗歌查询记录
    @GetMapping("")
    public List<PoemQuery> getAllPoemQueries() {
        return this.poemQueryRepository.findAll();
    }

    // 根据 ID 获取单个诗歌查询记录
    @GetMapping("/{id}")
    public ResponseEntity<PoemQuery> getPoemQueryById(@PathVariable Integer id) {
        PoemQuery poemQuery = this.poemQueryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Poem Query not found with id: " + id));
        return ResponseEntity.ok(poemQuery);
    }

    // 创建新诗歌查询记录
    @PostMapping("")
    public ResponseEntity<PoemQuery> createPoemQuery(@RequestBody PoemQuery newPoemQuery) {
        PoemQuery createdPoemQuery = this.poemQueryRepository.save(newPoemQuery);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPoemQuery);
    }

    // 更新诗歌查询信息
    @PutMapping("/{id}")
    public ResponseEntity<PoemQuery> updatePoemQuery(@PathVariable Integer id, @RequestBody PoemQuery poemQueryDetails) {
        PoemQuery existingPoemQuery = this.poemQueryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Poem Query not found with id: " + id));

        existingPoemQuery.setProjectId(poemQueryDetails.getProjectId());
        existingPoemQuery.setPoem(poemQueryDetails.getPoem());
        existingPoemQuery.setHeatmapPath(poemQueryDetails.getHeatmapPath());

        PoemQuery updatedPoemQuery = this.poemQueryRepository.save(existingPoemQuery);
        return ResponseEntity.ok(updatedPoemQuery);
    }

    // 删除诗歌查询记录
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePoemQuery(@PathVariable Integer id) {
        PoemQuery existingPoemQuery = this.poemQueryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Poem Query not found with id: " + id));

        this.poemQueryRepository.delete(existingPoemQuery);
        return ResponseEntity.noContent().build();
    }
}