package com.eban.social_media.Services.ServiceImpl;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Service
public class ImageService {
    // Hàm Resize ảnh
    public MultipartFile resizeImage(MultipartFile imageFile) throws IOException {
        // Tạo file tạm để lưu file đầu vào
        File inputFile = File.createTempFile("input-", imageFile.getOriginalFilename());
        imageFile.transferTo(inputFile);

        // Tạo file tạm cho ảnh đã resize và chuyển sang đuôi mở rộng là .jpg
        File resizedFile = File.createTempFile("resized-", ".jpg");

        // Resize ảnh sử dụng Thumbnailator
        Thumbnails.of(inputFile)
                .size(800, 600)// điều chỉnh lại kích thước ảnh với thoong số sau
                .toFile(resizedFile);

        // Chuyển đổi từ File sang MultipartFile
        FileInputStream inputStream = new FileInputStream(resizedFile);
        MultipartFile resizedMultipartFile = new MockMultipartFile(
                resizedFile.getName(),  // Tên file
                resizedFile.getName(),  // Tên file gốc
                "image/jpeg",           // Content type (giả sử định dạng là JPEG)
                inputStream              // InputStream từ file
        );

        // Xóa file tạm gốc
        inputFile.delete();
        resizedFile.delete();

        // Trả về MultipartFile
        return resizedMultipartFile;
    }
}
