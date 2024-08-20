package com.example.cookie_session.cookie.member;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cookie")
public class CookieMemberController {

    private final CookieMemberService memberService;
    private final CookieMemberRepository memberRepository;

    @PostMapping("/register")
    public String register(@RequestBody CookieRegisterMemberRequest request) {
        memberService.register(request);
        return "ok";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute CookieLoginMemberRequest request, HttpServletResponse response) {
        CookieMember loginMember = memberService.login(request);

        if(loginMember == null) {
            return "not find member";
        }

        if (loginMember != null) {
            Cookie cookie = new Cookie("memberId", String.valueOf(loginMember.getId()));
            response.addCookie(cookie);
        }

        return "find member";
    }

    @GetMapping("/home")
    public String home(@CookieValue(name = "memberId", required = false) Long memberId) {
        CookieMember findMember = memberRepository.findById(memberId).orElse(null);
        if (findMember == null) {
            return "not find member";
        }
        return findMember.getEmail();
    }
}
