package com.cobanogluhasan.springboot.repository;

import com.cobanogluhasan.springboot.model.RestorationRecords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestorationRecordsRepository extends JpaRepository<RestorationRecords, Integer> {
    // 可以在这里添加自定义查询方法
}