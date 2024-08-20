package com.example.cookie_session.session.member;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SessionLoginMemberRequest {

    private String email;
    private String password;

}
