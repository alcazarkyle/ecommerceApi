package com.ws101.alcazar.ecommerceapi.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "file.upload-dir")
public class FileStorageProperties {
    private String uploadDir;
}