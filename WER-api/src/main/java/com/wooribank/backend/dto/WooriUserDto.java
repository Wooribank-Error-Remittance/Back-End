package com.wooribank.backend.dto;

import com.wooribank.backend.vo.WooriUserVo;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class WooriUserDto {
    private final Long wooriUserId;
    private final String userId;
    private final String name;
    private final String phoneNumber;


    public static WooriUserDto of(WooriUserVo vo) {
        return builder()
                .wooriUserId(vo.getWooriUserId())
                .userId(vo.getUserId())
                .name(vo.getName())
                .phoneNumber(vo.getPhoneNumber())
                .build();
    }
}
