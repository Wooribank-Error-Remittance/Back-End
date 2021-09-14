package com.wooribank.backend.controller;

import com.wooribank.backend.component.CommonResponseMaker;
import com.wooribank.backend.constant.ResponseCode;
import com.wooribank.backend.dto.CommonResponse;
import com.wooribank.backend.dto.SignUpRequestDto;
import com.wooribank.backend.dto.WooriUserDto;
import com.wooribank.backend.service.DataCreateService;
import com.wooribank.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@RestController
public class DataCreateController extends AppApiV1Controller {

    private final DataCreateService dataCreateService;
    private final CommonResponseMaker commonResponseMaker;

    @PostMapping("/data/create")
    public CommonResponse<Void> dataCreate() throws IOException {

        dataCreateService.createData();

        return commonResponseMaker.makeEmptyInfoCommonResponse(ResponseCode.SUCCESS);
    }
}
