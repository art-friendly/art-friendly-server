package com.example.artfriendlyserver.auth.service;


import com.example.artfriendlyserver.application.KakaoApi;
import com.example.artfriendlyserver.auth.model.User;
import com.example.artfriendlyserver.auth.model.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final KakaoApi kakaoApi;
    private final UserRepository userRepository;
    public String kakoLogin (String code) throws JsonProcessingException {
        return kakaoApi.login(code);
    }
    public String validateUser (String token) throws JsonProcessingException {
        User user = User.parseFrom(kakaoApi.getUser(token));
        if(userRepository.findByKakaoId(user.getKakaoId()).isEmpty()){
            userRepository.save(user);
            return "신규회원. 관심 분야 선택 진행";
        }
        return "기존 회원";
    }

    public String kakoLogout (String token) throws JsonProcessingException {
        return kakaoApi.logout(token);
    }

    public String kakaoUnlink(String token){
        return kakaoApi.unlink(token);
    }


}
