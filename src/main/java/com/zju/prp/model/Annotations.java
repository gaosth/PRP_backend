package com.zju.prp.model;

import javax.persistence.*;

@Entity
@Table(name = "annotations")
public class Annotations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Annotation_ID")
    private Integer annotationId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Project_ID", nullable = false)
    private Projects project;

    @Column(name = "Axis", columnDefinition = "JSON", nullable = false)
    private String axis; // JSON 字段需要转换为字符串存储

    @Column(name = "Mix_Method", columnDefinition = "JSON", nullable = false)
    private String mixMethod; // JSON 字段需要转换为字符串存储

    // 默认构造函数
    public Annotations() {}

    // 全参构造函数
    public Annotations(Projects project, String axis, String mixMethod) {
        this.project = project;
        this.axis = axis;
        this.mixMethod = mixMethod;
    }

    // Getter 和 Setter 方法
    public Integer getAnnotationId() {
        return annotationId;
    }

    public void setAnnotationId(Integer annotationId) {
        this.annotationId = annotationId;
    }

    public Projects getProject() {
        return project;
    }

    public void setProject(Projects project) {
        this.project = project;
    }

    public String getAxis() {
        return axis;
    }

    public void setAxis(String axis) {
        this.axis = axis;
    }

    public String getMixMethod() {
        return mixMethod;
    }

    public void setMixMethod(String mixMethod) {
        this.mixMethod = mixMethod;
    }
}