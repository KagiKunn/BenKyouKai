package com.ltk.Benkyoukai.controller;

import com.ltk.Benkyoukai.dto.BenkyoukaiVO;
import com.ltk.Benkyoukai.service.BenkyoukaiService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static java.lang.System.out;

@Controller
public class HC {

    private final BenkyoukaiService benkyoukaiService;
    private final PasswordEncoder passwordEncoder;

    public HC(BenkyoukaiService benkyoukaiService, PasswordEncoder passwordEncoder) {
        this.benkyoukaiService = benkyoukaiService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/")
    public String showLoginPage(Model model) {
        return "login";
    }

    @PostMapping("/loginProcess")
    public String loginUser(@RequestParam String id, @RequestParam String password,
                            HttpSession session,
                            Model model, HttpServletResponse response) throws IOException {
//        if (user != null) {
//            session.setAttribute("user", user);
//            return "redirect:/userList";  // 로그인 성공 시 회원 리스트 페이지로 이동
//        } else {
//            response.setContentType("text/html; charset=euc-kr");
//            PrintWriter out = response.getWriter();
//            if (benkyoukaiService.getUserById(id) == null) {
//                out.println("<script>alert('없는 아이디입니다'); location.href='/'</script>");
//            } else {
//                out.println("<script>alert('비밀번호가 틀렸습니다'); location.href='/'</script>");
//            }
//            out.flush();
//            return "";
//        }

        BenkyoukaiVO user = benkyoukaiService.getUserById(id);

        if (user != null) {
            // 기존 비밀번호와 암호화된 비밀번호를 비교
            boolean isPasswordMatch = passwordEncoder.matches(password, user.getPassword());
            boolean isPlainPasswordMatch = password.equals(user.getPassword());

            // 디버그 로그 출력
            System.out.println("Encrypted Password match result: " + isPasswordMatch);
            System.out.println("Plain Password match result: " + isPlainPasswordMatch);

            if (isPasswordMatch || isPlainPasswordMatch) {
                // 성공: 세션에 유저 정보를 저장하고 리다이렉트
                session.setAttribute("user", user);
                return "redirect:/userList";
            } else {
                // 실패: 비밀번호가 틀린 경우
                response.setContentType("text/html; charset=euc-kr");
                PrintWriter out = response.getWriter();
                out.println("<script>alert('비밀번호가 틀렸습니다'); location.href='/'</script>");
                out.flush();
                return "";
            }
        } else {
            // 실패: 아이디가 존재하지 않는 경우
            response.setContentType("text/html; charset=euc-kr");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('없는 아이디입니다'); location.href='/'</script>");
            out.flush();
            return "";
        }

    }

    @GetMapping("/check-id")
    public ResponseEntity<Boolean> checkId(@RequestParam("id") String id) {
        out.println(id);
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

        benkyoukaiService.registerUser(benkyoukaiVO);

        return "login";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        // 세션에서 user 객체 제거
        session.invalidate();  // 세션을 무효화

        // 로그인 페이지로 리다이렉트
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") String id, Model model) {
        benkyoukaiService.deleteUser(id);

        List<BenkyoukaiVO> users = benkyoukaiService.getAllUsers();
        model.addAttribute("users", users);
        return "redirect:/userList";
    }

    @PostMapping("/edit/{id}")
    public String editUser(@PathVariable("id") String id, Model model) {
        out.println("Received ID: " + id); // 로그로 확인
        BenkyoukaiVO benkyoukaiVO = benkyoukaiService.getUserById(id);
        out.println("체크: " + benkyoukaiVO);
        model.addAttribute("user", benkyoukaiVO);
        return "edit";
    }

    @PostMapping("/edit")
    public String editUser(@ModelAttribute BenkyoukaiVO updatedUser) {
        benkyoukaiService.updateUser(updatedUser);
        return "redirect:/userList";
    }

//    @GetMapping("/show")
//    public String getUsers(Model model) {
//        model.addAttribute("users", benkyoukaiService.getAllUsers());
//        return "userlist";
//    }
}
