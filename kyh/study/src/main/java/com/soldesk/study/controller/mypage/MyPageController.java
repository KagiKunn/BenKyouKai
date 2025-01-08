package com.soldesk.study.controller.mypage;

import com.soldesk.study.dto.UserDTO;
import com.soldesk.study.service.MyPage.MyPageDAO;
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
import java.nio.file.Paths;
import java.util.UUID;

@Controller
@RequestMapping("/mypage")
public class MyPageController {
    @Autowired
    private MyPageDAO myPageDAO;
    @Value("${file.upload-dir}")
    private String uploadDir;

    //delete
    @PostMapping("/delete")
    @ResponseBody
    public int delete(@RequestBody UserDTO param) {
        myPageDAO.deleteUser(param.getId());
        return 1;
    }

    //update
    @PostMapping("/update")
    @ResponseBody
    public int update(@RequestParam("id") String id,
                      @RequestParam("pw") String pw,
                      @RequestParam("nick") String nickname,
                      @RequestPart("img") MultipartFile imageFile,
                      HttpSession session) {
        try {
            //crypt object
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            //set img path
            String imgPath = saveFile(imageFile);
            //check param
            UserDTO userDTO = new UserDTO(id, pw, nickname, null ,imgPath);
            //pw crypt
            String encodePw = passwordEncoder.encode(userDTO.getPw());
            //setting DTO
            userDTO.setPw(encodePw);

            UserDTO updatedUser = new UserDTO(id, encodePw, nickname, null, imgPath);
            UserDTO loginUser = (UserDTO) session.getAttribute("loginUserInfo");
            String loginId = loginUser.getId();
            myPageDAO.updateUser(userDTO.getId(), userDTO.getPw(), userDTO.getNick(),userDTO.getImg(), loginId);

            session.setAttribute("loginUserInfo", updatedUser); // 갱신된 정보로 세션 업데이트
            return 1;
        }catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    //update page
    @GetMapping("/updatepage")
    public String updatepage(HttpSession session, Model model) {
        UserDTO login_user = (UserDTO) session.getAttribute("loginUserInfo");
        model.addAttribute("login_user", login_user);
        return "mypage/updatePage";
    }

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
