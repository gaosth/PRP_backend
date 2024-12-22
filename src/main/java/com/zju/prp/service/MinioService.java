package com.zju.prp.service;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Service
public class MinioService {

    private final MinioClient minioClient;
    private final String bucketName = "prp-images"; // 使用您的桶名称

    @Autowired
    public MinioService(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    /**
     * 将文件上传到MinIO，并返回存储路径或对象ID。
     */
    public String uploadFileToMinio(MultipartFile file) throws Exception {
        String originalFileName = file.getOriginalFilename();
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String filePrefix = originalFileName.substring(0, originalFileName.lastIndexOf("."));
        String objectName = filePrefix + "-" + UUID.randomUUID().toString() + fileExtension;

        try (InputStream inputStream = file.getInputStream()) {
            minioClient.putObject(
                PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .stream(inputStream, file.getSize(), -1)
                    .contentType(file.getContentType())
                    .build()
            );
            return objectName; // 返回存储路径或对象ID
        }
    }
}