package com.example.cookie_session.cookie.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CookieMemberService {

    private final CookieMemberRepository memberRepository;

    @Transactional
    public CookieMember register(CookieRegisterMemberRequest request) {
        CookieMember registerMember = new CookieMember(request.getEmail(), request.getPassword());
        return memberRepository.save(registerMember);
    }

    @Transactional
    public CookieMember login(CookieLoginMemberRequest request) {
        CookieMember loginMember = memberRepository.findByEmailAndPassword(request.getEmail(),
            request.getPassword());
        return loginMember;
    }
}
