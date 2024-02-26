package com.example.artfriendlyserver.auth.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.simple.JSONObject;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private long kakaoId;
  private String nickname;
  private String bio;
  private String pictureUrl;
  private boolean isVerified;
  /*
 이후 추가할 할목 :
 관심분야, 담벼락,뱃지,취향테스트 결과
  */
  public static User parseFrom(JSONObject jsonObject) {
    return builder()
        .kakaoId(Long.parseLong(jsonObject.get("sub").toString()))
        .nickname( jsonObject.get("nickname").toString())
        .pictureUrl(jsonObject.get("picture").toString())
        .build();
  }

  public void updateNickname(String newNickname){
    this.nickname = newNickname;
  }
  public void updatePictureUrl(String imageUrl){
    this.pictureUrl = imageUrl;
  }
  public void verifyUser(){
    this.isVerified = true;
  }


}
