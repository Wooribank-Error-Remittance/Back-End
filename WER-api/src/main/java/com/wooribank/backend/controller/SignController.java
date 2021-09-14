package com.wooribank.backend.controller;

import com.wooribank.backend.component.CommonResponseMaker;
import com.wooribank.backend.dto.CommonResponse;
import com.wooribank.backend.dto.SignInRequestDto;
import com.wooribank.backend.dto.SignUpRequestDto;
import com.wooribank.backend.dto.WooriUserDto;
import com.wooribank.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@RestController
public class SignController extends AppApiV1Controller {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final CommonResponseMaker commonResponseMaker;

    @PostMapping("/sign/complete")
    public CommonResponse<WooriUserDto> completeSignUp(@RequestBody SignUpRequestDto requestDto) throws IOException {

        final WooriUserDto responseDto = WooriUserDto.of(userService.completeSignUp(requestDto.toVo()));

        return commonResponseMaker.makeSucceedCommonResponse(responseDto);
    }

    @PostMapping("/sign/login")
    public CommonResponse<WooriUserDto> signIn(@RequestBody SignInRequestDto requestDto) {

        final WooriUserDto responseDto = WooriUserDto.of(userService.signIn(requestDto.toVo()));

        return commonResponseMaker.makeSucceedCommonResponse(responseDto);
    }

}