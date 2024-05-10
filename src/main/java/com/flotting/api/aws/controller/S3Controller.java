package com.flotting.api.aws.controller;

import com.flotting.api.aws.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/s3")
@RequiredArgsConstructor
public class S3Controller {

    private final S3Service s3Service;

    /**
     * 그룹(팀) 생성
     *
     * @param file
     * @return
     */
    @PostMapping(path = "/upload")
    public List<String> upload(
            @RequestPart(value = "file", required = false) List<MultipartFile> file,
            @RequestParam String filePath
    ) {
        return s3Service.s3Upload(file, filePath);
    }
}
