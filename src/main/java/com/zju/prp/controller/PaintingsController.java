package com.zju.prp.controller;

import com.zju.prp.exception.ResourceNotFoundException;
import com.zju.prp.model.Paintings;
import com.zju.prp.repository.PaintingsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/paintings")
public class PaintingsController {

    private final PaintingsRepository paintingsRepository;

    // 构造函数注入，无需 @Autowired 注解
    public PaintingsController(PaintingsRepository paintingsRepository) {
        this.paintingsRepository = paintingsRepository;
    }

    // 获取所有绘画记录
    @GetMapping("")
    public List<Paintings> getAllPaintings() {
        return this.paintingsRepository.findAll();
    }

    // 根据 ID 获取单个绘画记录
    @GetMapping("/{id}")
    public ResponseEntity<Paintings> getPaintingById(@PathVariable Integer id) {
        Paintings painting = this.paintingsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Painting not found with id: " + id));
        return ResponseEntity.ok(painting);
    }

    // 创建新绘画记录
    @PostMapping("")
    public ResponseEntity<Paintings> createPainting(@RequestBody Paintings newPainting) {
        Paintings createdPainting = this.paintingsRepository.save(newPainting);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPainting);
    }

    // 更新绘画信息
    @PutMapping("/{id}")
    public ResponseEntity<Paintings> updatePainting(@PathVariable Integer id, @RequestBody Paintings paintingDetails) {
        Paintings existingPainting = this.paintingsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Painting not found with id: " + id));

        // 更新现有绘画记录的信息
        existingPainting.setMask(paintingDetails.getMask());
        existingPainting.setTensor(paintingDetails.getTensor());
        existingPainting.setRepresentativeColor(paintingDetails.getRepresentativeColor());
        existingPainting.setTitle(paintingDetails.getTitle());
        existingPainting.setArtist(paintingDetails.getArtist());
        existingPainting.setEra(paintingDetails.getEra());
        existingPainting.setMaterial(paintingDetails.getMaterial());
        existingPainting.setSize(paintingDetails.getSize());
        existingPainting.setInstitution(paintingDetails.getInstitution());
        existingPainting.setDescription(paintingDetails.getDescription());
        existingPainting.setPoem(paintingDetails.getPoem());
        existingPainting.setRemarks(paintingDetails.getRemarks());

        Paintings updatedPainting = this.paintingsRepository.save(existingPainting);
        return ResponseEntity.ok(updatedPainting);
    }

    // 删除绘画记录
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePainting(@PathVariable Integer id) {
        Paintings existingPainting = this.paintingsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Painting not found with id: " + id));

        this.paintingsRepository.delete(existingPainting);
        return ResponseEntity.noContent().build();
    }
}