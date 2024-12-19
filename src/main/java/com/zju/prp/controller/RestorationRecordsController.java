package com.zju.prp.controller;

import com.zju.prp.exception.ResourceNotFoundException;
import com.zju.prp.model.RestorationRecords;
import com.zju.prp.repository.RestorationRecordsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/restorationrecords")
public class RestorationRecordsController {

    private final RestorationRecordsRepository restorationRecordsRepository;

    // 构造函数注入，无需 @Autowired 注解
    public RestorationRecordsController(RestorationRecordsRepository restorationRecordsRepository) {
        this.restorationRecordsRepository = restorationRecordsRepository;
    }

    // 获取所有修复记录
    @GetMapping("")
    public List<RestorationRecords> getAllRestorationRecords() {
        return this.restorationRecordsRepository.findAll();
    }

    // 根据 ID 获取单个修复记录
    @GetMapping("/{id}")
    public ResponseEntity<RestorationRecords> getRestorationRecordById(@PathVariable Integer id) {
        RestorationRecords record = this.restorationRecordsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restoration Record not found with id: " + id));
        return ResponseEntity.ok(record);
    }

    // 创建新修复记录
    @PostMapping("")
    public ResponseEntity<RestorationRecords> createRestorationRecord(@RequestBody RestorationRecords newRecord) {
        RestorationRecords createdRecord = this.restorationRecordsRepository.save(newRecord);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRecord);
    }

    // 更新修复记录信息
    @PutMapping("/{id}")
    public ResponseEntity<RestorationRecords> updateRestorationRecord(@PathVariable Integer id, @RequestBody RestorationRecords recordDetails) {
        RestorationRecords existingRecord = this.restorationRecordsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restoration Record not found with id: " + id));

        existingRecord.setProject(recordDetails.getProject());
        existingRecord.setStep(recordDetails.getStep());
        existingRecord.setImageUrl(recordDetails.getImageUrl());

        RestorationRecords updatedRecord = this.restorationRecordsRepository.save(existingRecord);
        return ResponseEntity.ok(updatedRecord);
    }

    // 删除修复记录
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestorationRecord(@PathVariable Integer id) {
        RestorationRecords existingRecord = this.restorationRecordsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restoration Record not found with id: " + id));

        this.restorationRecordsRepository.delete(existingRecord);
        return ResponseEntity.noContent().build();
    }
}