package kr.co.teaspoon.controller;

import kr.co.teaspoon.dto.Member;
import kr.co.teaspoon.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/member/*")
public class MemberController {
    @Autowired
    private MemberService memberService;
    @Autowired
    HttpSession session;
    /* 관리자가 볼 수 있는 회원목록 */
    @RequestMapping(value="list.do", method = RequestMethod.GET)
    public String memberList(Model model) throws Exception {
        List<Member> memberList = memberService.memberList();
        model.addAttribute("memberList", memberList);
        model.addAttribute("title", "회원 목록");
        return "/member/memberList";
    }

    /* 관리자가 회원의 상세 정보 보기 */
    @GetMapping("detail.do")
    public String memberDetail(@RequestParam String id, Model model) throws Exception {
        Member member = memberService.getMember(id);
        model.addAttribute("member", member);
        return "/member/memberDetail";
    }
    // 일반 회원 본인 정보 보기
    @GetMapping("mypage.do")
    public String mypage(Model model) throws Exception {
        String id = (String) session.getAttribute("sid");
        Member member = memberService.getMember(id);
        model.addAttribute("member", member);
        return "/member/mypage";
    }

    //회원 가입 - 약관 동의 페이지 로딩
    @GetMapping("agree.do")
    public String getAgree(Model model) throws Exception {
        return "/member/agree";
    }
    //회원 가입 - 회원가입폼 페이지 로딩
    @GetMapping("join.do")
    public String getJoin(Model model) throws Exception {
        return "/member/memberInsert";
    }
    //회원 가입 - Ajax로 아이디 중복 체크
    @RequestMapping(value="idCheck.do", method=RequestMethod.POST)
    public void idCheck(HttpServletResponse response, HttpServletRequest request, Model model) throws Exception {

    }

    //회원 가입 - 회원 가입 처리
    @RequestMapping(value="insert.do", method = RequestMethod.POST)
    public String memberWrite(Member member, Model model) throws Exception {
        return "redirect:/";
    }

    //로그인 폼 로딩
    @RequestMapping("login.do")
    public String memberLoginForm(Model model) throws Exception {
        return "/member/loginForm";
    }
}
