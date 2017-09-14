package com.mamingjie.lovejr.config;

import com.aliyun.oss.OSSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AliyunOSSConfig {

    @Value("${aliyun.oss.endpoint}")
    String endpoint;
    @Value("${aliyun.oss.id}")
    String accessKeyId;
    @Value("${aliyun.oss.secret}")
    String accessKeySecret;

    @Bean
    public OSSClient ossClient() {
        return new OSSClient(endpoint, accessKeyId, accessKeySecret);
    }
}
