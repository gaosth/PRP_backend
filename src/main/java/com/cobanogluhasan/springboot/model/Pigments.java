package com.cobanogluhasan.springboot.model;

import javax.persistence.*;
import com.fasterxml.jackson.databind.JsonNode;

@Entity
@Table(name = "pigments")
public class Pigments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Pigment_ID")
    private Integer pigmentId;

    @Column(name = "Thickness", nullable = false)
    private Float thickness;

    @Column(name = "Material", nullable = false, length = 255)
    private String material;

    @Column(name = "Lightness", nullable = false, length = 50)
    private String lightness;

    @Column(name = "Spectrum", columnDefinition = "JSON", nullable = false)
    private String spectrum; // JSON 字段需要转换为字符串存储

    // 默认构造函数
    public Pigments() {}

    // 全参构造函数
    public Pigments(Float thickness, String material, String lightness, String spectrum) {
        this.thickness = thickness;
        this.material = material;
        this.lightness = lightness;
        this.spectrum = spectrum;
    }

    // Getter 和 Setter 方法
    public Integer getPigmentId() {
        return pigmentId;
    }

    public void setPigmentId(Integer pigmentId) {
        this.pigmentId = pigmentId;
    }

    public Float getThickness() {
        return thickness;
    }

    public void setThickness(Float thickness) {
        this.thickness = thickness;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getLightness() {
        return lightness;
    }

    public void setLightness(String lightness) {
        this.lightness = lightness;
    }

    public String getSpectrum() {
        return spectrum;
    }

    public void setSpectrum(String spectrum) {
        this.spectrum = spectrum;
    }
}