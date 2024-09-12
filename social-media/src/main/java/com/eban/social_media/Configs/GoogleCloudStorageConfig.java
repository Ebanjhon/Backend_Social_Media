package com.eban.social_media.Configs;

import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GoogleCloudStorageConfig {
    @Bean
    public Storage storage(){
        return StorageOptions.getDefaultInstance().getService();
    }
}
