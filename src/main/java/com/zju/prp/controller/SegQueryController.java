package com.zju.prp.controller;

import com.zju.prp.exception.ResourceNotFoundException;
import com.zju.prp.model.SegQuery;
import com.zju.prp.repository.SegQueryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/segqueries")
public class SegQueryController {

    private final SegQueryRepository segQueryRepository;

    // 构造函数注入，无需 @Autowired 注解
    public SegQueryController(SegQueryRepository segQueryRepository) {
        this.segQueryRepository = segQueryRepository;
    }

    // 获取所有分割查询记录
    @GetMapping("")
    public List<SegQuery> getAllSegQueries() {
        return this.segQueryRepository.findAll();
    }

    // 根据 ID 获取单个分割查询记录
    @GetMapping("/{id}")
    public ResponseEntity<SegQuery> getSegQueryById(@PathVariable Integer id) {
        SegQuery segQuery = this.segQueryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Seg Query not found with id: " + id));
        return ResponseEntity.ok(segQuery);
    }

    // 创建新分割查询记录
    @PostMapping("")
    public ResponseEntity<SegQuery> createSegQuery(@RequestBody SegQuery newSegQuery) {
        SegQuery createdSegQuery = this.segQueryRepository.save(newSegQuery);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSegQuery);
    }

    // 更新分割查询信息
    @PutMapping("/{id}")
    public ResponseEntity<SegQuery> updateSegQuery(@PathVariable Integer id, @RequestBody SegQuery segQueryDetails) {
        SegQuery existingSegQuery = this.segQueryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Seg Query not found with id: " + id));

        existingSegQuery.setProject(segQueryDetails.getProject());
        existingSegQuery.setPhoto(segQueryDetails.getPhoto());
        existingSegQuery.setSlice(segQueryDetails.getSlice());

        SegQuery updatedSegQuery = this.segQueryRepository.save(existingSegQuery);
        return ResponseEntity.ok(updatedSegQuery);
    }

    // 删除分割查询记录
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSegQuery(@PathVariable Integer id) {
        SegQuery existingSegQuery = this.segQueryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Seg Query not found with id: " + id));

        this.segQueryRepository.delete(existingSegQuery);
        return ResponseEntity.noContent().build();
    }
}