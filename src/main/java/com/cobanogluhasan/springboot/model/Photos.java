package com.cobanogluhasan.springboot.model;

import javax.persistence.*;

@Entity
@Table(name = "photos")
public class Photos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Photo_ID")
    private Integer photoId;

    @Column(name = "Species", length = 255)
    private String species;

    @Column(name = "Minio_ID", nullable = false, length = 255)
    private String minioId;

    // 默认构造函数
    public Photos() {}

    // 全参构造函数
    public Photos(String species, String minioId) {
        this.species = species;
        this.minioId = minioId;
    }

    // Getter 和 Setter 方法
    public Integer getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Integer photoId) {
        this.photoId = photoId;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getMinioId() {
        return minioId;
    }

    public void setMinioId(String minioId) {
        this.minioId = minioId;
    }
}