package com.flotting.api.aws.service;

import com.flotting.common.aws.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class S3Service {
    private final S3Uploader s3Uploader;

    @Transactional
    public List<String> s3Upload(List<MultipartFile> file, String filePath) {
        return s3Uploader.uploadFileToS3(file, "static/" + filePath);
    }

    @Transactional
    public String s3Delete(String imageUrl) {
        return s3Uploader.deleteS3(imageUrl);
    }


}
