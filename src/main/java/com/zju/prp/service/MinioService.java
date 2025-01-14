package com.zju.prp.service;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import io.minio.GetPresignedObjectUrlArgs;
import java.util.concurrent.TimeUnit;
import io.minio.errors.MinioException;
import io.minio.http.Method;

import java.io.InputStream;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class MinioService {

    private final MinioClient minioClient;
    private final String bucketName = "prp-images"; // 使用您的桶名称
    private static final Logger logger = LoggerFactory.getLogger(MinioService.class);

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

    public String uploadFileToMinioUnsafe(MultipartFile file) throws Exception {
        String originalFileName = file.getOriginalFilename();
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String filePrefix = originalFileName.substring(0, originalFileName.lastIndexOf("."));
        String objectName = filePrefix + "-" + UUID.randomUUID().toString() + fileExtension;

        String unsafeObjectName = "/resource/" + objectName;

        try (InputStream inputStream = file.getInputStream()) {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(unsafeObjectName)
                            .stream(inputStream, file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build()
            );
            return unsafeObjectName; // 返回存储路径或对象ID
        }
    }

    /**
     * 生成MinIO对象的预签名URL。
     * 暂不启用
     */
    public String generatePresignedUrl(String objectName) throws Exception {
        try {
            // 生成有效期为7天的预签名URL
            String url = minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .method(Method.GET)
                            .expiry(7, TimeUnit.DAYS) // 设置URL的有效期
                            .build()
            );

            return url;
        } catch (MinioException e) {
            logger.error("Unexpected error occurred while generating presigned URL for objectName {}: {}", objectName, e.getMessage(), e);
            throw new Exception("Error occurred while generating presigned URL", e);
        }
    }
}