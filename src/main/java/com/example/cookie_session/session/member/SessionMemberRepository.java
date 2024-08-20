package com.example.cookie_session.session.member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionMemberRepository extends JpaRepository<SessionMember, Long> {

    SessionMember findByEmailAndPassword(String email, String password);

}
