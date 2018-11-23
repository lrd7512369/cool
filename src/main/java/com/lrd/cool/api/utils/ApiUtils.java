package com.lrd.cool.api.utils;

import com.lrd.cool.api.ResponseCode;
import com.lrd.cool.api.response.ApiResult;

public class ApiUtils {

    public static ApiResult getResultForInvalidParams(String message) {
        ApiResult result = new ApiResult();
        result.setCode(ResponseCode.CODE_INVALID_PARAMS);
        result.setMessage(message);
        result.setValue(null);
        return result;
    }

    public static ApiResult getResultForSuccessOperation(String message) {
        return getResultForSuccessOperation(message, null);
    }

    public static ApiResult getResultForSuccessOperation(String message, Object value) {
        ApiResult result = new ApiResult();
        result.setCode(ResponseCode.CODE_SUCCESS);
        result.setMessage(message);
        result.setValue(value);
        return result;
    }
}
