package com.zju.prp.model;

import javax.persistence.*;

@Entity
@Table(name = "projects", 
       uniqueConstraints = {
           @UniqueConstraint(columnNames = "Project_ID")
       })
public class Projects {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Project_ID")
    private Integer projectId;

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

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "Image_ID", referencedColumnName = "Image_ID", nullable = true)
    private UploadImages image;

    @Column(name = "Status", length = 50)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_ID", nullable = false)
    private Users user;

    @Column(name = "Ref", columnDefinition = "JSON")
    private String ref; // JSON 字段需要转换为字符串存储

    // 默认构造函数
    public Projects() {}

    // 全参构造函数
    public Projects(String title, String artist, String era, String material, String size, String institution, String description, String poem, UploadImages image, String status, Users user, String ref) {
        this.title = title;
        this.artist = artist;
        this.era = era;
        this.material = material;
        this.size = size;
        this.institution = institution;
        this.description = description;
        this.poem = poem;
        this.image = image;
        this.status = status;
        this.user = user;
        this.ref = ref;
    }

    // Getter 和 Setter 方法
    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getEra() {
        return era;
    }

    public void setEra(String era) {
        this.era = era;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPoem() {
        return poem;
    }

    public void setPoem(String poem) {
        this.poem = poem;
    }

    public UploadImages getImage() {
        return image;
    }

    public void setImage(UploadImages image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }
}