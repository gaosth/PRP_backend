package com.zju.prp.controller;

import com.zju.prp.exception.ResourceNotFoundException;
import com.zju.prp.model.Paintings;
import com.zju.prp.repository.PaintingsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin
@RequestMapping("/paintings")
public class PaintingsController {

    private final PaintingsRepository paintingsRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

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

    // 部分更新绘画信息
    @PatchMapping("/{id}")
    public ResponseEntity<Paintings> patchPainting(@PathVariable Integer id, @RequestBody Map<String, Object> fields) {
        Paintings existingPainting = this.paintingsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Painting not found with id: " + id));

        // 更新现有绘画记录的信息
        fields.forEach((key, value) -> {
            try {
                switch (key) {
                    case "mask":
                        if (value instanceof String) {
                            existingPainting.setMask((String) value);
                        } else {
                            existingPainting.setMask(objectMapper.writeValueAsString(value));
                        }
                        break;
                    case "tensor":
                        if (value instanceof String) {
                            existingPainting.setTensor((String) value);
                        } else {
                            existingPainting.setTensor(objectMapper.writeValueAsString(value));
                        }
                        break;
                    case "representativeColor":
                        if (value instanceof String) {
                            existingPainting.setRepresentativeColor((String) value);
                        } else {
                            throw new IllegalArgumentException("Invalid type for field: " + key);
                        }
                        break;
                    case "title":
                        if (value instanceof String) {
                            existingPainting.setTitle((String) value);
                        } else {
                            throw new IllegalArgumentException("Invalid type for field: " + key);
                        }
                        break;
                    case "artist":
                        if (value instanceof String) {
                            existingPainting.setArtist((String) value);
                        } else {
                            throw new IllegalArgumentException("Invalid type for field: " + key);
                        }
                        break;
                    case "era":
                        if (value instanceof String) {
                            existingPainting.setEra((String) value);
                        } else {
                            throw new IllegalArgumentException("Invalid type for field: " + key);
                        }
                        break;
                    case "material":
                        if (value instanceof String) {
                            existingPainting.setMaterial((String) value);
                        } else {
                            throw new IllegalArgumentException("Invalid type for field: " + key);
                        }
                        break;
                    case "size":
                        if (value instanceof String) {
                            existingPainting.setSize((String) value);
                        } else {
                            throw new IllegalArgumentException("Invalid type for field: " + key);
                        }
                        break;
                    case "institution":
                        if (value instanceof String) {
                            existingPainting.setInstitution((String) value);
                        } else {
                            throw new IllegalArgumentException("Invalid type for field: " + key);
                        }
                        break;
                    case "description":
                        if (value instanceof String) {
                            existingPainting.setDescription((String) value);
                        } else {
                            throw new IllegalArgumentException("Invalid type for field: " + key);
                        }
                        break;
                    case "poem":
                        if (value instanceof String) {
                            existingPainting.setPoem((String) value);
                        } else {
                            throw new IllegalArgumentException("Invalid type for field: " + key);
                        }
                        break;
                    case "remarks":
                        if (value instanceof String) {
                            existingPainting.setRemarks((String) value);
                        } else {
                            existingPainting.setRemarks(objectMapper.writeValueAsString(value));
                        }
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid field: " + key);
                }
            } catch (Exception e) {
                throw new RuntimeException("Error updating field " + key, e);
            }
        });

        Paintings updatedPainting = this.paintingsRepository.save(existingPainting);
        return ResponseEntity.ok(updatedPainting);
    }
}