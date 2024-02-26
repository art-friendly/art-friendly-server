package com.example.artfriendlyserver.common.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommonController {
  @GetMapping("/")
  public String home(){
    return "home";
  }
  @GetMapping("/test")
  public String test(){
    return "test";
  }
}
