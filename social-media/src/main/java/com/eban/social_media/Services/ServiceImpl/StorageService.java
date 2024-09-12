package com.eban.social_media.Services.ServiceImpl;

import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.storage.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class StorageService {
    private final Storage storage;

    public StorageService() throws IOException {
        ClassPathResource resource = new ClassPathResource("socialmediaeban-3f9033932820.json");
        storage = StorageOptions.newBuilder()
                .setCredentials(ServiceAccountCredentials.fromStream(resource.getInputStream()))
                .build()
                .getService();
    }

    public String uploadFile(MultipartFile file) throws IOException {
        // Specify the bucket name
        String bucketName = "eban_social_media";  // Thay thế bằng tên bucket của bạn

        // Lấy tên file từ MultipartFile
        String fileName = file.getOriginalFilename();

        // Tạo BlobId với bucket và tên file
        BlobId blobId = BlobId.of(bucketName, fileName);

        // Kiểm tra Content-Type của tệp được upload
        String contentType = file.getContentType();

        // Kiểm tra nếu Content-Type hợp lệ (hình ảnh hoặc video)
        if (contentType == null || (!contentType.startsWith("image/") && !contentType.startsWith("video/"))) {
            throw new IOException("File không phải là định dạng hình ảnh hoặc video hợp lệ!");
        }

        // Đặt Content-Type dựa trên loại file được tải lên (image/jpeg, image/png, video/mp4, etc.)
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                .setContentType(contentType)  // Đặt Content-Type từ file
                .build();

        // Upload file lên Google Cloud Storage
        Blob blob = storage.create(blobInfo, file.getBytes());

        // Tạo URL công khai để truy cập file
        String publicUrl = String.format("https://storage.googleapis.com/%s/%s", bucketName, fileName);

        return publicUrl;
    }
}
