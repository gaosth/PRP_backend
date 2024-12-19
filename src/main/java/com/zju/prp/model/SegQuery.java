package com.zju.prp.model;

import javax.persistence.*;

@Entity
@Table(name = "segqueries")
public class SegQuery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SQ_ID")
    private Integer sqId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Project_ID", nullable = false, referencedColumnName = "Project_ID")
    private Projects project;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Photo_ID", nullable = false, referencedColumnName = "Photo_ID")
    private Photos photo;

    @Column(name = "Slice", columnDefinition = "JSON", nullable = false)
    private String slice; // JSON 字段需要转换为字符串存储

    // 默认构造函数
    public SegQuery() {}

    // 全参构造函数
    public SegQuery(Projects project, Photos photo, String slice) {
        this.project = project;
        this.photo = photo;
        this.slice = slice;
    }

    // Getter 和 Setter 方法

    // SQ ID
    public Integer getSqId() {
        return sqId;
    }

    public void setSqId(Integer sqId) {
        this.sqId = sqId;
    }

    // Project
    public Projects getProject() {
        return project;
    }

    public void setProject(Projects project) {
        this.project = project;
    }

    // Photo
    public Photos getPhoto() {
        return photo;
    }

    public void setPhoto(Photos photo) {
        this.photo = photo;
    }

    // Slice
    public String getSlice() {
        return slice;
    }

    public void setSlice(String slice) {
        this.slice = slice;
    }
}