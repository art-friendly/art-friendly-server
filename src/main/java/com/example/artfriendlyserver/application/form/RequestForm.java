package com.example.artfriendlyserver.application.form;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@RequiredArgsConstructor
@Component
public class RequestForm {
    @Value("${kakao.grant-type}")
    private String grantType;
    @Value("${kakao.client-id}")
    private String clientId;
    @Value("${kakao.client-secret}")
    private String clientSecret;
    @Value("${kakao.redirect-uri.login}")
    private String loginRedirectUri;
    @Value("${kakao.redirect-uri.logout}")
    private String logoutRedirectUri;
    private String code;

    public HttpEntity<MultiValueMap<String, String>>  requestToken(String code){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
        params.add("redirect_uri", loginRedirectUri);
        params.add("code", code);
        return new HttpEntity<>(params, headers);
    }

    public HttpEntity<MultiValueMap<String, String>> makeHeadermWithToken (String accessToken){

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        headers.add("Authorization", accessToken);
        return new HttpEntity<>(headers);
    }
}
