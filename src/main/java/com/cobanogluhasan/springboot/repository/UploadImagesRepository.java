package com.cobanogluhasan.springboot.repository;

import com.cobanogluhasan.springboot.model.UploadImages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadImagesRepository extends JpaRepository<UploadImages, Integer> {
    // 可以在这里添加自定义查询方法
}