package com.togethertp.web;

import com.togethertp.domain.user.User;
import com.togethertp.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.togethertp.web.dto.SignupDto;

@RequiredArgsConstructor
@Controller //1.IoC에 등록이 됐다는 의미 2.파일을 리턴하는 컨트롤러
public class AuthController {


    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    private final AuthService authService;

    @GetMapping("/auth/signin")
    public String signinForm() {
        return "auth/signin";
    }

    @GetMapping("/auth/signup")
    public String signupForm() {
        return "auth/signup";
    }

    // 회원가입버튼 -> /auth/signup -> /auth/signin
    @PostMapping("/auth/signup")
    public String signup(SignupDto signupDto) { // form으로 데이터가 날아오면 데이터 형식이 key=value 형식으로 데이터가 들어온다. (x-www.form-urlencoded 방식)
        log.info(signupDto.toString());
        // User 오브젝트에 SignupDto 데이터를 넣어야함
        User user = signupDto.toEntity();
        log.info(user.toString());
        User userEntity = authService.회원가입(user);
        System.out.println(userEntity);
        return "auth/signin";
    }
}