package com.example.cookie_session.session.member;

import com.example.cookie_session.cookie.member.CookieMember;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

@RestController
@RequiredArgsConstructor
@RequestMapping("/session")
public class SessionMemberController {

    private final SessionMemberService memberService;
    private final SessionMemberRepository memberRepository;

    private final static String SESSION_COOKIE_NAME = "JSESSIONID";

    @PostMapping("/register")
    public String register(@RequestBody SessionRegisterMemberRequest request) {
        memberService.register(request);
        return "ok";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute SessionLoginMemberRequest request, HttpServletRequest servletRequest) {
        SessionMember loginMember = memberService.login(request);

        if (loginMember != null) {
            HttpSession session = servletRequest.getSession();
            session.setAttribute(SESSION_COOKIE_NAME, loginMember.getId());
        }

        return "ok";
    }

    @GetMapping("/home")
    public String home(@SessionAttribute(name = SESSION_COOKIE_NAME, required = false) Long memberId) {
        SessionMember findMember = memberRepository.findById(memberId).orElse(null);
        if (findMember == null) {
            return "not find member";
        }
        return findMember.getEmail();
    }
}
