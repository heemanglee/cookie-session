package com.example.cookie_session.cookie.member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CookieMemberRepository extends JpaRepository<CookieMember, Long> {

    CookieMember findByEmailAndPassword(String email, String password);

}
