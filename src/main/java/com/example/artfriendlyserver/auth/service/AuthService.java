package com.example.artfriendlyserver.auth.service;


import com.example.artfriendlyserver.application.KakaoApi;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final KakaoApi kakaoApi;
    public String kakoLogin (String code) throws JsonProcessingException {
        return kakaoApi.login(code);
    }
    public String validateUser (String token) throws JsonProcessingException {

        return kakaoApi.getUser(token).toString();
    }

    public String kakoLogout (String token) throws JsonProcessingException {
        return kakaoApi.logout(token);
    }

    public String kakaoUnlink(String token){
        return kakaoApi.unlink(token);
    }


}
