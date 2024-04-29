package com.example.hello.controller;

import com.example.hello.dto.UserRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/get")
public class GetApiController {

    @GetMapping(path = "/hello")
    public String hello() {
        return "Get Hello";
    }

    // 예전 방식 (단, path만 지정할 경우 모든 http 메서드에 반응)
    @RequestMapping(path = "/hi", method = RequestMethod.GET)  // cf) ctrl+space=자동완성
    public String hi() {
        return "Hi";
    }

    /**
    @GetMapping("/path-variable/{name}")  // 계속 바뀌는 부분은 path-variable 사용
    public String pathVariable(@PathVariable String name) {
        System.out.println("pathVariable = " + name);
        return name;
    }
    */

    // 위랑 같은데, 변수 이름을 path variable 이름이랑 다른 거 써줘야 하는 경우
    @GetMapping("/path-variable/{name}")
    public String pathVariable(@PathVariable(name = "name") String pathName) {
        System.out.println("pathVariable : " + pathName);
        return pathName;
    }

    // query parameter : 검색할 때 여러가지 매개변수 인자
    // ?key1=value1&key2=value2... 의 형식
    // ? 다음부터 query parameter 올 수 있고, 다음 key-value 오려면 &로 연결
    // http://localhost:9090/api/get/query-param?user=steve&email=steve@gmail.com&age=30

    // query-param 받는 방법1 - Map으로 받기 -> key가 어떤 정보가 들어올지 알 수 없음
    // 무한정 query-param을 받을 수 있음
    @GetMapping(path = "query-param")
    public String queryParam(@RequestParam Map<String, String> queryParam) {
        StringBuilder sb = new StringBuilder();

        // map에 들어있는 모든 key-value 쌍 print
        queryParam.entrySet().forEach( entry -> {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
            System.out.println("\n");

            // key-value 정보 저장
            sb.append(entry.getKey() + " = " + entry.getValue() + "\n");
        });
        return sb.toString();
    }

    // query param 받는 방법2 - 들어올 query-param 명시
    // 순서, 내용 맞게 보내야 함. 아니면 에러 + 나중에 query-param 추가되면 일일이 코드 추가해줘야 함
    @GetMapping(path = "query-param02")
    public String queryParam02(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam int age
    ) {
        System.out.println(name);
        System.out.println(email);
        System.out.println(age);

        return name + " " + email + " " + age;
    }

    // query-param 받는 방법3 - 받을 query-param 정보 모아둔 클래스 만들어놓고 해당 객체를 query-param으로 받기
    // 주소 안의 key 읽고 객체의 필드들 읽어서 Spring Boot가 알아서 대조함.
    // -> @RequestParam Annotation 붙아면 안 됨.
    // 가장 현업에서 많이 쓰는 방법
    @GetMapping("query-param03")
    public String queryParam03(UserRequest userRequest) {
        System.out.println(userRequest.getName());
        System.out.println(userRequest.getEmail());
        System.out.println(userRequest.getAge());

        return userRequest.toString();
    }
}
