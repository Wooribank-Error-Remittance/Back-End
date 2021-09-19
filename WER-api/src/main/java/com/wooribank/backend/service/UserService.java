package com.wooribank.backend.service;

import com.wooribank.backend.constant.ResponseCode;
import com.wooribank.backend.domain.FCMToken;
import com.wooribank.backend.domain.WooriUser;
import com.wooribank.backend.exception.CommonException;
import com.wooribank.backend.repository.WooriUserRepository;
import com.wooribank.backend.vo.SignInRequestVo;
import com.wooribank.backend.vo.SignUpRequestVo;
import com.wooribank.backend.vo.WooriUserVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final WooriUserRepository wooriUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public WooriUserVo completeSignUp(SignUpRequestVo requestVo) {

        final boolean isValidUserId = !wooriUserRepository.findTopByUserId(requestVo.getUserId()).isPresent();

        if (!isValidUserId) {
            throw new CommonException(ResponseCode.SIGN_UP_FAILED_FOR_INVALID_INFO);
        }

        final String encryptedPassword = passwordEncoder.encode(requestVo.getPassword());

        final WooriUser wooriUser = new WooriUser(
                requestVo.getUserId(),
                encryptedPassword,
                requestVo.getName(),
                requestVo.getPhoneNumber());

        wooriUserRepository.save(wooriUser);

        return wooriUser.toVo();
    }

    @Transactional
    public WooriUserVo signIn(SignInRequestVo requestVo) {

        final Optional<WooriUser> wooriUserOptional =
                wooriUserRepository.findTopByUserId(requestVo.getUserId());

        final WooriUser wooriUser =
                wooriUserOptional.orElseThrow(() -> new CommonException(ResponseCode.USER_NOT_EXISTED));

        if (!passwordEncoder.matches(requestVo.getPassword(), wooriUser.getPassword())) {
            throw new CommonException(ResponseCode.INVALID_PASSWORD);
        }

        final FCMToken fcmToken = new FCMToken(requestVo.getFcmToken(),wooriUser);
        wooriUser.registerToken(fcmToken);

        return wooriUser.toVo();
    }


}
