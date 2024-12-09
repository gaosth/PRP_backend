package com.cobanogluhasan.springboot.model;

import javax.persistence.*;
import com.fasterxml.jackson.databind.JsonNode;

@Entity
@Table(name = "poemqueries")
public class PoemQuery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PQ_ID")
    private Integer pqId;

    @Column(name = "Project_ID", nullable = false)
    private Integer projectId;

    @Column(name = "Poem", columnDefinition = "TEXT", nullable = false)
    private String poem;

    @Column(name = "Heatmap_Path", columnDefinition = "JSON", nullable = false)
    private String heatmapPath; // JSON 字段需要转换为字符串存储

    // 默认构造函数
    public PoemQuery() {}

    // 全参构造函数
    public PoemQuery(Integer projectId, String poem, String heatmapPath) {
        this.projectId = projectId;
        this.poem = poem;
        this.heatmapPath = heatmapPath;
    }

    // Getter 和 Setter 方法

    // PQ ID
    public Integer getPqId() {
        return pqId;
    }

    public void setPqId(Integer pqId) {
        this.pqId = pqId;
    }

    // Project ID
    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    // Poem
    public String getPoem() {
        return poem;
    }

    public void setPoem(String poem) {
        this.poem = poem;
    }

    // Heatmap Path
    public String getHeatmapPath() {
        return heatmapPath;
    }

    public void setHeatmapPath(String heatmapPath) {
        this.heatmapPath = heatmapPath;
    }
}