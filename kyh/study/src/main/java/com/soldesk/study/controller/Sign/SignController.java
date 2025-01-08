package com.soldesk.study.controller.Sign;

import com.soldesk.study.dto.UserDTO;
import com.soldesk.study.service.Sign.SignUpDAO;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/sign")
public class SignController {
    @Autowired
    private SignUpDAO signDAO;

    @Value("${file.upload-dir}")
    private String uploadDir;

    //crypt object
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @GetMapping("/goSignUp")
    public String goSignUp() {
        return "sign/signUp";
    }

    @GetMapping("/result")
    public String goSignResult(Model model) {
        List<UserDTO> userDTOs = signDAO.getAllUsers();
        model.addAttribute("users", userDTOs);
        return "result";
    }

    @GetMapping("/doCheckId")
    @ResponseBody //json 타입으로 반환값
    public int doCheckId(@RequestParam("id") String id) {
        if(signDAO.getUserById(id) != null) {
            return 0;
        } else {
            return 1;
        }
    }

    @PostMapping("/doSignUp")
    public String doSignUp(@RequestParam("id") String id,
                           @RequestParam("pw") String pw,
                           @RequestParam("nick") String nickname,
                           @RequestParam("age") String age,
                           @RequestPart("img") MultipartFile imageFile) {
        try {
            //save img
            String imgPath = saveFile(imageFile);
            //set userDTO
            UserDTO userDTO = new UserDTO(id, pw, nickname, age, imgPath);
            userDTO.setId(id);
            userDTO.setPw(pw);
            userDTO.setNick(nickname);
            userDTO.setAge(age);
            userDTO.setImg(imgPath);
            //pw crypt
            String encodePw = passwordEncoder.encode(pw);

            //set UserDTO
            userDTO.setPw(encodePw);

            int signUpRes = signDAO.insertUser(userDTO);
            if (signUpRes == 1) {
                System.out.println("가입 성공");
                return "result";
            } else {
                System.out.println("가입 실패");
                return "redirect:/";
            }
        }catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @PostMapping("/doSign")
    @ResponseBody
    public int doSign(@RequestBody UserDTO param,
                               HttpServletRequest request) {
        String id = param.getId();
        String pw = param.getPw();
        UserDTO loginUserInfo = signDAO.getUserDTOById(id);

        String encodePw = passwordEncoder.encode(param.getPw());
        boolean isMatch = passwordEncoder.matches(param.getPw(), encodePw);

        //pw match
        if (isMatch && loginUserInfo != null) {
                HttpSession session = request.getSession();
                session.setAttribute("loginUserInfo", loginUserInfo);

                return 1;
        } else {
            System.out.println("실패");
            return 0;
        }
    }

    //save file
    private String saveFile(MultipartFile file) throws IOException {
        //파일 없으면 에러
        if (file.isEmpty()) {
            throw new IOException("파일 비어있는디유?");
        }

        //파일 생성
        String fileName = UUID.randomUUID()+"_"+file.getOriginalFilename();
        String path = Paths.get(uploadDir, fileName).toAbsolutePath().toString();

        //파일 업로드 폴더 없을 경우 생성
        File uploadDirFile = new File(uploadDir);
        if (!uploadDirFile.exists()) {
            System.out.println("생성하니?");
            uploadDirFile.mkdirs();  // 디렉토리 생성
        }

        file.transferTo(new File(path));  // 파일을 지정된 경로로 저장
        return "/images/"+fileName; // img 태그 소스경로
    }
}