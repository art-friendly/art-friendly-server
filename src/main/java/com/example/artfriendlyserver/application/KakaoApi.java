package com.example.artfriendlyserver.application;
import com.example.artfriendlyserver.application.form.RequestForm;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class KakaoApi {
    private final RequestForm requestForm;
    public String login(String code) throws JsonProcessingException {
        RestTemplate rt = new RestTemplate();
        ResponseEntity<JSONObject> response = rt.exchange(
                "https://kauth.kakao.com/oauth/token", // https://{요청할 서버 주소}
                HttpMethod.POST, // 요청할 방식
                requestForm.requestToken(code), // 요청할 때 보낼 데이터
                JSONObject.class // 요청 시 반환되는 데이터 타입
        );

       return Objects.requireNonNull(response.getBody()).toJSONString();
    }

    public String logout(String token){
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(
                "https://kapi.kakao.com/v1/user/logout",
                HttpMethod.POST,
                requestForm.makeHeadermWithToken(token),
                String.class // 요청 시 반환되는 데이터 타입
        );
        return response.getBody();
    }

    public String unlink(String token){
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(
                "https://kapi.kakao.com/v1/user/unlink",
                HttpMethod.POST,
                requestForm.makeHeadermWithToken(token),
                String.class // 요청 시 반환되는 데이터 타입
        );
        return response.getBody();
    }

    public JSONObject getUser(String token){
        RestTemplate rt = new RestTemplate();
        ResponseEntity<JSONObject> response = rt.exchange(
                "https://kapi.kakao.com/v1/oidc/userinfo", // https://{요청할 서버 주소}
                HttpMethod.GET, // 요청할 방식
                requestForm.makeHeadermWithToken(token), // 요청할 때 보낼 데이터
                JSONObject.class // 요청 시 반환되는 데이터 타입
        );
        return response.getBody();
    }
}
