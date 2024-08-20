package com.example.cookie_session.cookie.member;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CookieLoginMemberRequest {

    private String email;
    private String password;

}
