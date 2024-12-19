package com.zju.prp.controller;

import com.zju.prp.exception.ResourceNotFoundException;
import com.zju.prp.model.Pigments;
import com.zju.prp.repository.PigmentsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/pigments")
public class PigmentsController {

    private final PigmentsRepository pigmentsRepository;

    // 构造函数注入，无需 @Autowired 注解
    public PigmentsController(PigmentsRepository pigmentsRepository) {
        this.pigmentsRepository = pigmentsRepository;
    }

    // 获取所有颜料记录
    @GetMapping("")
    public List<Pigments> getAllPigments() {
        return this.pigmentsRepository.findAll();
    }

    // 根据 ID 获取单个颜料记录
    @GetMapping("/{id}")
    public ResponseEntity<Pigments> getPigmentById(@PathVariable Integer id) {
        Pigments pigment = this.pigmentsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pigment not found with id: " + id));
        return ResponseEntity.ok(pigment);
    }

    // 创建新颜料记录
    @PostMapping("")
    public ResponseEntity<Pigments> createPigment(@RequestBody Pigments newPigment) {
        Pigments createdPigment = this.pigmentsRepository.save(newPigment);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPigment);
    }

    // 更新颜料信息
    @PutMapping("/{id}")
    public ResponseEntity<Pigments> updatePigment(@PathVariable Integer id, @RequestBody Pigments pigmentDetails) {
        Pigments existingPigment = this.pigmentsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pigment not found with id: " + id));

        existingPigment.setThickness(pigmentDetails.getThickness());
        existingPigment.setMaterial(pigmentDetails.getMaterial());
        existingPigment.setLightness(pigmentDetails.getLightness());
        existingPigment.setSpectrum(pigmentDetails.getSpectrum());

        Pigments updatedPigment = this.pigmentsRepository.save(existingPigment);
        return ResponseEntity.ok(updatedPigment);
    }

    // 删除颜料记录
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePigment(@PathVariable Integer id) {
        Pigments existingPigment = this.pigmentsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pigment not found with id: " + id));

        this.pigmentsRepository.delete(existingPigment);
        return ResponseEntity.noContent().build();
    }
}