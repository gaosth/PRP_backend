package com.zju.prp.repository;

import com.zju.prp.model.StarProjects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StarProjectsRepository extends JpaRepository<StarProjects, Integer> {
    // 可以在这里添加自定义查询方法，例如查找特定用户的收藏项目
    List<StarProjects> findByUserUserId(Integer userId);
}