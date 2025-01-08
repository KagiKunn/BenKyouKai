package com.cjh.Benkyoukai.Controller;

import com.cjh.Benkyoukai.DTO.RegisterVO;
import com.cjh.Benkyoukai.Service.PasswordService;
import com.cjh.Benkyoukai.Service.RegisterService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class RegisterController {


    @Autowired
    private RegisterService registerService;

    @Autowired
    private PasswordService passwordService;

    @Value("${upload.path}")  // application.properties에서 파일 경로 설정을 읽어옴
    private String UPLOAD_DIR;


    @GetMapping("/")
    public String Home(HttpSession session, Model model) {
        Boolean loggedIn = (Boolean) session.getAttribute("loggedIn");

        if (loggedIn == null) {
            loggedIn = false;
        }
        System.out.println(loggedIn);


        model.addAttribute("loggedIn", loggedIn);
        return "Home";
    }

    @GetMapping("/register")
    public String register() {
        return "Register";
    }

    @GetMapping("/registery")
    public @ResponseBody String registery(RegisterVO registerVO, @RequestParam String id, @RequestParam String pw, @RequestParam String name, @RequestParam int age) {
        registerVO.setId(id);
        String encryptedPassword = passwordService.encryptPassword(registerVO.getPw());
        registerVO.setPw(encryptedPassword);
        registerVO.setName(name);
        registerVO.setAge(age);
        System.out.println(registerVO);


        int validCheck = registerService.validChecker(registerVO);
        System.out.println(validCheck);
        if (validCheck == 1) {

            return "same";
        } else {
            int check = registerService.registerAccount(registerVO);
            System.out.println(check);
            return "okay";
        }


    }

    @GetMapping("/show")
    public String show(Model model) {
        model.addAttribute("accounts", registerService.selectAllAccount());
        System.out.println(registerService.selectAllAccount());
        return "Registery";
    }

    @GetMapping("/login")
    public String login() {
        return "Login";
    }

    @GetMapping("/tryLogin")
    public @ResponseBody String tryLogin(@RequestParam String id, @RequestParam String pw, HttpSession session) {
        RegisterVO registerVO = new RegisterVO();
        registerVO.setId(id);

        String dbpw = registerService.selectPasswordById(registerVO);

        boolean passwordMatch = passwordService.matchesPassword(pw, dbpw);


        System.out.println(registerVO);

        int loginCheck = registerService.loginAccount(registerVO);
        System.out.println(loginCheck);
        if (id == null) {
            return "denied , no id submitted";

        }
        if (!passwordMatch) {
            return "denied wrong password";
        } else {

            session.setAttribute("loggedIn", true);
            session.setAttribute("id", registerVO.getId());

            //pw session에 가급적으로 안넣을것
            return "approved";
        }

    }

    @GetMapping("/mypage")
    public String myPage(HttpSession session, RegisterVO registerVO, Model model) {


        registerVO.setId(session.getAttribute("id").toString());

        System.out.println(registerService.selectAccountById(registerVO));
        model.addAttribute("accountInfo", registerService.selectAccountById(registerVO));


        return "MyPage";
    }

    @GetMapping("/update")
    public String update(RegisterVO registerVO, Model model) {

        model.addAttribute("accountInfo", registerVO);

        return "Update";
    }


    @GetMapping("/updateInfo")
    public String updateInfo(RegisterVO registerVO, HttpSession session) {

        String encryptedPassword = passwordService.encryptPassword(registerVO.getPw());
        registerVO.setPw(encryptedPassword);
        int check = registerService.updateAccount(registerVO);
        if (check == 1) {
            System.out.println("update success");

        } else {
            System.out.println("update fail");
        }

        return "redirect:/mypage";
    }

    ;

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/delete")
    public @ResponseBody String delete(@RequestParam String id, HttpSession session) {
        RegisterVO registerVO = new RegisterVO();
        registerVO.setId(id);
        int check = registerService.deleteAccountById(registerVO);
        if (check == 1) {
            System.out.println("delete success");
            session.invalidate();
            return "deleted";
        } else {
            return "delete fail";
        }


    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, Model model) throws IOException {
        if (file.isEmpty()) {
            model.addAttribute("error", "Please select a file to upload.");
            return "/mypage";  // 업로드 실패 시 페이지 그대로 돌아가기
        }

        // 절대 경로로 설정 (디버깅용 경로 확인 출력)
        String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/images/";
        File directory = new File(uploadDir);

        // 디렉터리가 없으면 생성
        if (!directory.exists()) {
            boolean dirsCreated = directory.mkdirs();  // 디렉터리 생성 여부
            if (dirsCreated) {
                System.out.println("Directory created: " + uploadDir);
            } else {
                System.out.println("Failed to create directory: " + uploadDir);
                model.addAttribute("error", "Failed to create upload directory.");
                return "redirect:/mypage";  // 디렉터리 생성 실패 시 처리
            }
        }
        File oldProfileImage = new File(uploadDir + "profile");
        if (oldProfileImage.exists()) {
            if (oldProfileImage.delete()) {
                System.out.println("Old profile image deleted.");
            } else {
                System.out.println("Failed to delete old profile image.");
            }
        }
        // 파일 이름 및 경로 설정
        // 새 파일의 이름을 "profile"로 설정
        String filename = "profile.png";  // 파일 이름을 "profile"로 고정
        String filepath = uploadDir + filename;

        // 파일을 지정된 경로에 저장
        try {
            file.transferTo(new File(filepath));
            System.out.println("New profile image saved at: " + filepath);  // 경로 확인
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
            model.addAttribute("error", "File upload failed.");
            return "redirect:/mypage";  // 업로드 실패 시 처리
        }
        // 모델에 파일 이름 추가


        return "redirect:/";  // 파일 업로드 후 리다이렉트
    }
}