package com.example.cookie_session.session.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SessionMemberService {

    private final SessionMemberRepository memberRepository;

    @Transactional
    public SessionMember register(SessionRegisterMemberRequest request) {
        SessionMember registerMember = new SessionMember(request.getEmail(), request.getPassword());
        return memberRepository.save(registerMember);
    }

    @Transactional
    public SessionMember login(SessionLoginMemberRequest request) {
        SessionMember loginMember = memberRepository.findByEmailAndPassword(request.getEmail(),
            request.getPassword());
        return loginMember;
    }
}
