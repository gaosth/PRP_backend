package com.zju.prp.repository;

import com.zju.prp.model.Annotations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnotationsRepository extends JpaRepository<Annotations, Integer> {
    // 可以在这里添加自定义查询方法
}