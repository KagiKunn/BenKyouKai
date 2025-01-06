package com.ltk.Benkyoukai.controller;

import com.ltk.Benkyoukai.dto.BenkyoukaiVO;
import com.ltk.Benkyoukai.service.BenkyoukaiService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
public class HC {

    private final BenkyoukaiService benkyoukaiService;

    public HC(BenkyoukaiService benkyoukaiService) {
        this.benkyoukaiService = benkyoukaiService;
    }

    @GetMapping("/")
    public String showLoginPage(Model model) {
        return "login";
    }

    @PostMapping("/loginProcess")
    public String loginUser(@RequestParam String id, @RequestParam String password,
                            HttpSession session,
                            Model model, HttpServletResponse response) throws IOException {
        BenkyoukaiVO user = benkyoukaiService.getUser(id, password);
        System.out.println("유저 정보 : " + user);
        if (user != null) {
            session.setAttribute("user", user);
            return "redirect:/userList";  // 로그인 성공 시 회원 리스트 페이지로 이동
        } else {
            response.setContentType("text/html; charset=euc-kr");
            PrintWriter out = response.getWriter();
            if (benkyoukaiService.getUserById(id) == null) {
                out.println("<script>alert('없는 아이디입니다'); location.href='/'</script>");
            } else {
                out.println("<script>alert('비밀번호가 틀렸습니다'); location.href='/'</script>");
            }
            out.flush();
            return "";
        }
    }

    @GetMapping("/check-id")
    public ResponseEntity<Boolean> checkId(@RequestParam("id") String id) {
        System.out.println(id);
        boolean isDuplicate = benkyoukaiService.isIdDuplicate(id);
        return ResponseEntity.ok(isDuplicate);
    }

    @GetMapping("/userList")
    public String userList(HttpSession session, Model model) {
        // 세션에서 사용자 정보 확인
        BenkyoukaiVO loggedInUser = (BenkyoukaiVO) session.getAttribute("user");
        if (loggedInUser != null) {
           model.addAttribute("loggedInUser", loggedInUser); // 로그인되지 않은 경우 로그인 페이지로 리다이렉트
        }

        // 사용자 정보 및 목록 페이지 표시
        List<BenkyoukaiVO> users = benkyoukaiService.getAllUsers();
        model.addAttribute("users", users);
        return "userList";
    }

    @GetMapping("/main")
    public String registerPage() {
        return "main";
    }

    @PostMapping("/users/register")
    public String registerUser(BenkyoukaiVO benkyoukaiVO, HttpSession session ,Model model) {

        if(benkyoukaiService.isIdDuplicate(benkyoukaiVO.getId())){
            model.addAttribute("errorMessage", "이미 사용 중인 ID입니다!");
        }
        return "userList";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        // 세션에서 user 객체 제거
        session.invalidate();  // 세션을 무효화

        // 로그인 페이지로 리다이렉트
        return "redirect:/login";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") String id, Model model) {
        benkyoukaiService.deleteUser(id);

        List<BenkyoukaiVO> users = benkyoukaiService.getAllUsers();
        model.addAttribute("users", users);
        return "redirect:/userlist";
    }

//    @GetMapping("/show")
//    public String getUsers(Model model) {
//        model.addAttribute("users", benkyoukaiService.getAllUsers());
//        return "userlist";
//    }
}
