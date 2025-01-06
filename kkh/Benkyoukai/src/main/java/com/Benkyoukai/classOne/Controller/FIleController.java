package com.Benkyoukai.classOne.Controller;

import com.Benkyoukai.Service.FileService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
public class FIleController {

    private final FileService fileService;

    public FIleController(FileService fileService) {
        this.fileService = fileService;
    }

    @Value("${file.upload-dir}")
    private String uploadDir;

    @PostMapping("imgUpload")
    public String imgUpload(@Param("img") MultipartFile img) {
        String fileName = UUID.randomUUID().toString() + "_" + img.getOriginalFilename();
        if (fileName != null) {
            // static/uploads 디렉토리 경로
            Path uploadPath = Paths.get(uploadDir);

            // 디렉토리 존재 여부 확인, 없으면 생성
            File uploadDir = uploadPath.toFile();
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // 파일을 static/uploads 폴더에 저장
            try {
                File fileToSave = new File(uploadDir, fileName);
                img.transferTo(fileToSave);
                fileService.uploadImg(fileName);
                System.out.println("파일 저장 성공: " + fileToSave.getAbsolutePath());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "uploadedImg";  // 업로드 후 반환할 뷰
    }
}
