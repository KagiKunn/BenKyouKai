package com.ltk.Benkyoukai.controller;

import com.ltk.Benkyoukai.service.UploadService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
public class FileController {

    private final UploadService uploadService;

    public FileController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @Value("${file.upload.dir}")
    private String fileDir;

    @GetMapping ("/file")
    public String getFileForm(Model model) {

        return "file";
    }


    @PostMapping("/file")
    public String postFileForm(
            @RequestParam String name,
            @RequestParam MultipartFile file,
            RedirectAttributes redirectAttributes
    ) {
        try {
            // 디렉토리 생성
            File dir = new File(fileDir);
            if (!dir.exists()) {
                Files.createDirectories(Paths.get(fileDir));
            }

            // 파일 검증
            if (file.isEmpty()) {
                System.out.println("1");
                redirectAttributes.addFlashAttribute("message", "파일을 업로드해주세요.");
                return "redirect:/file";
            }

            String originalFilename = file.getOriginalFilename();
            String extension = "";

            System.out.println("오리지널 파일 네임 :" + originalFilename);

            if (originalFilename != null && originalFilename.contains(".")) {
                System.out.println("2");
                extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            }

            if (!extension.equalsIgnoreCase("jpg") && !extension.equalsIgnoreCase("png")) {
                System.out.println("3");
                redirectAttributes.addFlashAttribute("message", "지원되지 않는 파일 형식입니다. (JPG, PNG만 허용)");
                return "redirect:/file";
            }

            if (file.getSize() > 10 * 1024 * 1024) {// 10MB 제한
                System.out.println("4");
                redirectAttributes.addFlashAttribute("message", "파일 크기는 5MB 이하만 가능합니다.");
                return "redirect:/file";
            }

            // 파일 저장 (중복 방지)
            String filename = UUID.randomUUID() + "_" + originalFilename;
            uploadService.upload(filename);
            String uniqueFilename = fileDir + "/" + filename;
            System.out.println("유니크파일네임 : " + uniqueFilename);
            file.transferTo(new File(uniqueFilename));
            redirectAttributes.addFlashAttribute("message", "파일 업로드 성공!");
            System.out.println("5");

        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("message", "파일 업로드 실패: " + e.getMessage());
        }
        return "redirect:/file";
    }
}