package com.lrd.cool.api;

import com.lrd.cool.api.response.ApiResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author lierdong
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ApiResult handleGlobalException(Exception e) {
        e.printStackTrace();
        ApiResult result = new ApiResult();
        result.setCode(ResponseCode.CODE_ERROR);
        result.setMessage("请求异常");
        result.setValue(e.getMessage());
        return result;
    }

    @ExceptionHandler(value = BusinessException.class)
    public ApiResult handleBusinessException(BusinessException e){
        e.printStackTrace();
        ApiResult result = new ApiResult();
        result.setCode(e.getCode());
        result.setMessage(e.getMessage());
        result.setValue(null);
        return result;
    }
}
