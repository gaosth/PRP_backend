package com.cobanogluhasan.springboot.model;

import javax.persistence.*;

@Entity
@Table(name = "starprojects")
public class StarProjects {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Star_ID")
    private Integer starId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "User_ID", nullable = false)
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Project_ID", nullable = false)
    private Projects project;

    // 默认构造函数
    public StarProjects() {}

    // 全参构造函数
    public StarProjects(Users user, Projects project) {
        this.user = user;
        this.project = project;
    }

    // Getter 和 Setter 方法
    public Integer getStarId() {
        return starId;
    }

    public void setStarId(Integer starId) {
        this.starId = starId;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Projects getProject() {
        return project;
    }

    public void setProject(Projects project) {
        this.project = project;
    }
}