package com.example.hello.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController  // REST controller로 동작하는 클래스임을 명시 (REST API를 처리하는 컨트롤러)
@RequestMapping("/api")  // URI를 지정해주는 annotation
public class ApiController {
    // 요청을 받는 쪽을 '컨트롤러' 라고 함.

    // "http://localhost:9090/api/hello" 주소로 GET 요청 들어오면 문자열 return
    // localhost는 내 컴퓨터 의미함
    @GetMapping("/hello")
    public String hello() {
        return "Hello, Spring Boot!";
    }
}
