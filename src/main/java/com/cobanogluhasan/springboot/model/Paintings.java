package com.cobanogluhasan.springboot.model;

import javax.persistence.*;

@Entity
@Table(name = "paintings")
public class Paintings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Painting_ID")
    private Integer paintingId;

    @Column(name = "Mask", columnDefinition = "JSON", nullable = false)
    private String mask;

    @Column(name = "Tensor", columnDefinition = "JSON", nullable = false)
    private String tensor;

    @Column(name = "Representative_Color", nullable = false, length = 50)
    private String representativeColor;

    @Column(name = "Title", nullable = false, length = 255)
    private String title;

    @Column(name = "Artist", length = 255)
    private String artist;

    @Column(name = "Era", length = 255)
    private String era;

    @Column(name = "Material", length = 255)
    private String material;

    @Column(name = "Size", length = 50)
    private String size;

    @Column(name = "Institution", length = 255)
    private String institution;

    @Column(name = "Description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "Poem", columnDefinition = "TEXT")
    private String poem;

    @Column(name = "Remarks", columnDefinition = "JSON", nullable = false)
    private String remarks;

    // 默认构造函数
    public Paintings() {}

    // 全参构造函数
    public Paintings(String mask, String tensor, String representativeColor, String title, String artist,
                     String era, String material, String size, String institution, String description,
                     String poem, String remarks) {
        this.mask = mask;
        this.tensor = tensor;
        this.representativeColor = representativeColor;
        this.title = title;
        this.artist = artist;
        this.era = era;
        this.material = material;
        this.size = size;
        this.institution = institution;
        this.description = description;
        this.poem = poem;
        this.remarks = remarks;
    }

    // Getter 和 Setter 方法

    // Painting ID
    public Integer getPaintingId() {
        return paintingId;
    }

    public void setPaintingId(Integer paintingId) {
        this.paintingId = paintingId;
    }

    // Mask
    public String getMask() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask = mask;
    }

    // Tensor
    public String getTensor() {
        return tensor;
    }

    public void setTensor(String tensor) {
        this.tensor = tensor;
    }

    // Representative Color
    public String getRepresentativeColor() {
        return representativeColor;
    }

    public void setRepresentativeColor(String representativeColor) {
        this.representativeColor = representativeColor;
    }

    // Title
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Artist
    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    // Era
    public String getEra() {
        return era;
    }

    public void setEra(String era) {
        this.era = era;
    }

    // Material
    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    // Size
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    // Institution
    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    // Description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Poem
    public String getPoem() {
        return poem;
    }

    public void setPoem(String poem) {
        this.poem = poem;
    }

    // Remarks
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}