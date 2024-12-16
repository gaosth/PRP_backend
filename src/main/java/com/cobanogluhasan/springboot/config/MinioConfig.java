package com.cobanogluhasan.springboot.config;

import io.minio.MinioClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioConfig {

    private static final Logger logger = LoggerFactory.getLogger(MinioConfig.class);

    @Value("${minio.endpoint}")
    private String endpoint;

    @Value("${minio.accessKey}")
    private String accessKey;

    @Value("${minio.secretKey}")
    private String secretKey;

    @Value("${minio.secure}")
    private boolean secure;

    @Bean
    public MinioClient minioClient() {
        try {
            // 构建完整的端点URL
            String fullEndpoint = buildFullEndpoint();

            return MinioClient.builder()
                    .endpoint(fullEndpoint)
                    .credentials(accessKey, secretKey)
                    .build();
        } catch (Exception e) {
            logger.error("Failed to create MinioClient: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to initialize MinioClient", e);
        }
    }

    /**
     * Helper method to construct the full endpoint URL with SSL.
     */
    private String buildFullEndpoint() {
        if (secure) {
            return "https://" + endpoint;
        } else {
            return "http://" + endpoint;
        }
    }
}