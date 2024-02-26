package com.example.artfriendlyserver.auth.controller;

import com.example.artfriendlyserver.auth.model.type.Genre;
import com.example.artfriendlyserver.auth.service.AuthService;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
  private final AuthService authService;
  @GetMapping("/oauth/kakao/login")
  public ResponseEntity<String> kakaoLogin(@RequestParam(name = "code") String code) throws JsonProcessingException {
    return ResponseEntity.ok(authService.kakoLogin(code));
  }

  @GetMapping("/validate/users")
  public ResponseEntity<String> validateUser(@RequestHeader(name = "Authorization") String token) throws JsonProcessingException {
    return ResponseEntity.ok(authService.validateUser(token));
  }
  @GetMapping("oauth/kakao/logout")
  public ResponseEntity<String> kakaoLogout(@RequestHeader(name = "Authorization") String token) throws JsonProcessingException {
    return ResponseEntity.ok(authService.kakoLogout(token)); // 로그아웃 아이디 반환됨
  }

  @GetMapping("oauth/kakao/unlink")
  public ResponseEntity<String> kakaoUnlink(@RequestHeader(name = "Authorization") String token)  {
    return ResponseEntity.ok(authService.kakaoUnlink(token)); // 연결 해제된 아이디 반환됨
  }
}
