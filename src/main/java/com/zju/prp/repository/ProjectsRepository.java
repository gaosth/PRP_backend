package com.zju.prp.repository;

import com.zju.prp.model.Projects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectsRepository extends JpaRepository<Projects, Integer> {
    // 可以在这里添加自定义查询方法
    @Query(value = "select Project_Id, Title, Image_Id from projects", nativeQuery = true)
    List<Projects> findAllSimply();
}