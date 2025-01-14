package com.zju.prp.model;

import javax.persistence.*;

@Entity
@Table(name = "restorationrecords")
public class RestorationRecords {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Record_ID")
    private Integer recordId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Project_ID", nullable = false)
    private Projects project;

    @Column(name = "Step", nullable = false)
    private Integer step;

    @Column(name = "Image_Url", nullable = false, length = 255)
    private String imageUrl;

    // 默认构造函数
    public RestorationRecords() {}

    // 全参构造函数
    public RestorationRecords(Projects project, Integer step, String imageUrl) {
        this.project = project;
        this.step = step;
        this.imageUrl = imageUrl;
    }

    // Getter 和 Setter 方法
    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Projects getProject() {
        return project;
    }

    public void setProject(Projects project) {
        this.project = project;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}