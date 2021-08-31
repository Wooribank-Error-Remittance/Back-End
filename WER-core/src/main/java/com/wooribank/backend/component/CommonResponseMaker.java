package com.wooribank.backend.component;

import com.wooribank.backend.constant.ResponseCode;
import com.wooribank.backend.dto.CommonResponse;
import org.springframework.stereotype.Component;

@Component
public class CommonResponseMaker {
    public <T> CommonResponse<T> makeSucceedCommonResponse(
            T response
    ) {
        final CommonResponse<T> wrappedResponse = new CommonResponse<>();
        wrappedResponse.setData(response);
        return wrappedResponse;
    }

    public CommonResponse<Void> makeEmptyInfoCommonResponse(
            ResponseCode responseCode
    ) {
        final CommonResponse<Void> commonResponse = new CommonResponse<>();
        commonResponse.setCode(responseCode.getCode());
        commonResponse.setMessage(responseCode.getMessage());
        return commonResponse;
    }

    public CommonResponse<Void> makeFailedCommonResponse() {
        return makeEmptyInfoCommonResponse(ResponseCode.UNKNOWN_ERROR);
    }
}
